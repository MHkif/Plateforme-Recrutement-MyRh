package mhkif.yc.myrh.service.UserDetailsServiceImpls;

import mhkif.yc.myrh.config.security.authenticators.AuthenticatedCompany;
import mhkif.yc.myrh.repository.CompanyRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("companyUserDetails")
@AllArgsConstructor
@Slf4j
public class CompanyUserDetails implements UserDetailsService {
    private final CompanyRepo companyRepository;
    @Override
    public UserDetails loadUserByUsername(String email) {
        var company = companyRepository.findByEmail(email);

        if(company.isPresent()){
            AuthenticatedCompany authCompany = new AuthenticatedCompany(company.get());
            log.info("Company Name: "+company.get().getName());
            return authCompany;
        }else{
            throw new UsernameNotFoundException("Company with email not found : "+email);
        }

    }
}