package mhkif.yc.myrh.config.security.providers;

import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class CompanyAuthenticationProvider {
        /*implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    private  CompanyRepo companyRepository;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        String password = token.getCredentials().toString();

        Company company = companyRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("Company Not Found "));
        if (company == null || !passwordEncoder.matches(password, company.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        // Build a fully authenticated object with details
        AuthenticatedCompany authCompany = new AuthenticatedCompany(company);
        return new UsernamePasswordAuthenticationToken(authCompany, password, token.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

         */
}
