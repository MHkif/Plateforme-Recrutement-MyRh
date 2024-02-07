package mhkif.yc.myrh.config.security.providers;

import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class AdminAuthenticationProvider {
        /*
        implements AuthenticationProvider {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepo adminRepository;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();


        Admin admin = adminRepository.findByEmail(username);
        if (admin == null || !passwordEncoder.matches(token.getCredentials().toString(), admin.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // Build a fully authenticated object with details
        AuthenticatedAdmin authAdmin = new AuthenticatedAdmin(admin);
        return new UsernamePasswordAuthenticationToken(authAdmin, token.getCredentials().toString(), token.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    */
}
