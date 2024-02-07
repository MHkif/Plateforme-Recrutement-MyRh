package mhkif.yc.myrh.service.impl;

import mhkif.yc.myrh.dto.CompanySubscribeResponse;
import mhkif.yc.myrh.dto.requests.CompanyReq;
import mhkif.yc.myrh.dto.responses.CompanyRes;
import mhkif.yc.myrh.enums.SubscriptionStatus;
import mhkif.yc.myrh.exception.BadRequestException;
import mhkif.yc.myrh.mapper.CompanyMapper;
import mhkif.yc.myrh.model.Company;
import mhkif.yc.myrh.model.Confirmation;
import mhkif.yc.myrh.repository.CompanyRepo;
import mhkif.yc.myrh.repository.ConfirmationRepo;
import mhkif.yc.myrh.service.CompanySubscriptionService;
import mhkif.yc.myrh.service.ICompanyService;
import mhkif.yc.myrh.service.IEmailService;
import mhkif.yc.myrh.service.PaymentService;
import com.stripe.exception.StripeException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService , CompanySubscriptionService {

    private final CompanyRepo repository;
    private final ConfirmationRepo confirmationRepo;
    // : INJECT THE PAYMENT SERVICE
    private PaymentService paymentService;
    private final CompanyMapper mapper;
    private final IEmailService emailService;
    @Value("${spring.mail.properties.verify.host}")
    private String host;

    @Autowired
    public CompanyServiceImpl(CompanyRepo repository, CompanyMapper mapper, ConfirmationRepo confirmationRepo, PaymentService paymentService, IEmailService emailService) {
        this.repository = repository;
        this.mapper = mapper;
        this.paymentService = paymentService;
        this.emailService = emailService;
        this.confirmationRepo = confirmationRepo;
    }


    @Override
    public CompanyRes getById(Integer id) {
        Optional<Company> company = repository.findById(id);
        return company.map(mapper::toRes).orElseThrow(() -> new EntityNotFoundException("Comapny Not Exist with the given Id : " + id)
        );
    }

    @Override
    public Page<CompanyRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }

    @Override
    public CompanyRes create(CompanyReq request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Email Already Taken");
        }

        request.setEnabled(false);
        Company company = repository.save(mapper.reqToEntity(request));
        CompanyRes res = mapper.toRes(company);

        Confirmation confirmation = new Confirmation(company);
        confirmationRepo.save(confirmation);

        String name = res.getName();
        String sendingTo = res.getEmail();
        String url = "company/auth/confirm-account/";
        String subject = "MyRH : Email Verification ";
        String body = verificationEmailMessage(name, url, this.host,confirmation.getToken());
        emailService.sendSimpleMailMessage(name, sendingTo, subject, body);
        return res;

    }

    @Override
    public CompanyRes auth(String email, String password) {
        if (repository.existsByEmail(email)) {
            Company company = repository.findByEmail(email).get();
            if (!Objects.equals(company.getPassword(), password)) {
                throw new IllegalStateException("Incorrect Passowrd");
            }
            return  mapper.toRes(company);

        } else {
            throw new EntityNotFoundException("No Company Found with this Email");
        }
    }

    @Override
    public CompanyRes update(Integer id, CompanyRes res) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Boolean verifyToken(String token) throws Exception {
        Confirmation confirmation = confirmationRepo.findByToken(token);
        if(Objects.isNull(confirmation)){
            throw  new EntityNotFoundException("Token Not Found ");
        }

        boolean isLessThan3Minutes = this.validateDate(confirmation.getCreatedDate());

         if (!isLessThan3Minutes && !confirmation.isVerified()) {
            throw new BadRequestException(("Expired Token : " + token));
        }else if(confirmation.isVerified()){
            throw new Exception("Your Account is Already verified .");
        }

        Optional<Company> company = repository.findByEmail(confirmation.getCompany().getEmail());
        if (company.isEmpty()) {
            return Boolean.FALSE;
        }

        company.get().setEnabled(true);
        repository.save(company.get());

       confirmation.setVerified(true);
       confirmation.setVerifiedAt(LocalDateTime.now());
       confirmationRepo.save(confirmation);
        return Boolean.TRUE;
    }

    @Override
    public Boolean sendVerification(String token) throws Exception {
        Confirmation confirmation = confirmationRepo.findByToken(token);
        if(Objects.isNull(confirmation)){
            throw  new EntityNotFoundException("Token Not Found ");
        }else if(confirmation.isVerified()){
        throw new Exception("Your Account is Already verified .");
    }


        Optional<Company> company = repository.findByEmail(confirmation.getCompany().getEmail());
        if (company.isEmpty()) {
            throw new EntityNotFoundException("Company not found with the given credential.");
        }

        Confirmation new_confirmation = new Confirmation(company.get());
        confirmationRepo.delete(confirmation); // we have to delete this first before inserting another record in DB
        confirmationRepo.save(new_confirmation);


        String name = company.get().getName();
        String sendingTo = company.get().getEmail();
        String url = "company/auth/confirm-account/";
        String subject = "MyRH : Re-Send Verification ";
        String body = verificationEmailMessage(name, url, this.host,new_confirmation.getToken());
        emailService.sendSimpleMailMessage(name, sendingTo, subject, body);

        return null;
    }

    public boolean validateDate(LocalDateTime date) {
        return LocalDateTime.now().getMinute() - date.getMinute() <= 3;

    }

    private String verificationEmailMessage(String name, String url, String host, String token) {
        return "Hello " + name + ", \n\n" +
                "You account has been created successfully," +
                " Please click the link below to verify your account . \n\n" +
                host + url + token + " \n\n" +
                "Note : Link will be expired after 3 minutes. \n\n" +
                "The Support Team MyRH .";


    }


    @Override
    public SubscriptionStatus getSubscriptionStatus(String companyId) {
        return this.repository.findById(Integer.parseInt(companyId))
                .orElseThrow(() -> new EntityNotFoundException("Company Not Found"))
                .getSubscription();
    }

    @Override
    public boolean subscribe(String companyId, SubscriptionStatus subscriptionStatus , String token) {
        //: FIRST VERIFY IS THE COMPANY IS VALID WITH THE SAME SUBSCRIPTION
        if (getSubscriptionStatus(companyId).equals(subscriptionStatus)) {
            throw new BadRequestException("You are already subscribed to this subscription");
        }
        double amount = getAmountBasedOnSubscriptionType(subscriptionStatus);
        //: THEN VERIFY IF THE COMPANY HAS ENOUGH MONEY TO PAY FOR THE SUBSCRIPTION
        try{
            boolean isPay = this.paymentService.pay(token , amount);
            if(isPay){
                //: THEN UPDATE THE COMPANY SUBSCRIPTION
                Company company = this.repository.findById(Integer.parseInt(companyId))
                        .orElseThrow(() -> new EntityNotFoundException("Company Not Found"));
                company.setSubscription(subscriptionStatus);
                this.repository.save(company);
                return true;
            }
        }catch (StripeException e){
            throw new BadRequestException("Payment Failed"+ e.getCode());
        }
        return false;
    }

    @Override
    public CompanySubscribeResponse pay(String companyId, SubscriptionStatus subscriptionStatus, String token) {
        return null;
    }

    private double getAmountBasedOnSubscriptionType(SubscriptionStatus subscriptionStatus) {
        switch (subscriptionStatus){
            case BASIC:
                return 500.0;
            case PREMIUM:
                return 1000.0;
            default:
                return 0.0;
        }
    }

    @Override
    public boolean unsubscribe(String companyId) {
        return false;
    }
}
