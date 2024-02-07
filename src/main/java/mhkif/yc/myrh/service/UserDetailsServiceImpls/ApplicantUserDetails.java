package mhkif.yc.myrh.service.UserDetailsServiceImpls;

import mhkif.yc.myrh.config.security.authenticators.AuthenticatedApplicant;
import mhkif.yc.myrh.repository.JobSeekerRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("applicantUserDetails")
@AllArgsConstructor
@Slf4j
public class ApplicantUserDetails implements UserDetailsService {
    private final JobSeekerRepo applicantRepo;
    @Override
    public UserDetails loadUserByUsername(String email) {
        var applicant = applicantRepo.findByEmail(email);
        if(applicant.isPresent()){
            AuthenticatedApplicant authApplicant = new AuthenticatedApplicant(applicant.get());
            log.info("Applicant Name: "+applicant.get().getFirst_name());
            log.info("Applicant Authorities : "+authApplicant.getAuthorities());
            return authApplicant;
        }else{
            throw new UsernameNotFoundException("Applicant with email not found : "+email);
        }

    }
}