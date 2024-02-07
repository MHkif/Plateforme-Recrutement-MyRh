package mhkif.yc.myrh.service.UserDetailsServiceImpls;

import mhkif.yc.myrh.config.security.authenticators.AuthenticatedAdmin;
import mhkif.yc.myrh.repository.AdminRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("adminUserDetails")
@AllArgsConstructor
@Slf4j
public class AdminUserDetails implements UserDetailsService {
    private final AdminRepo adminRepository;
    @Override
    public UserDetails loadUserByUsername(String email) {
        var admin = adminRepository.findByEmail(email);

        if(admin != null){
            AuthenticatedAdmin authAdmin = new AuthenticatedAdmin(admin);

            log.info("Admin Name: "+admin.getFirst_name());
            log.info("Admin Authorities : "+authAdmin.getAuthorities());
            return authAdmin;
        }else{
            throw new UsernameNotFoundException("Admin with email not found : "+email);
        }

    }
}