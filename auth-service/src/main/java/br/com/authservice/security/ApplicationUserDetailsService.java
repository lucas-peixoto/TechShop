package br.com.authservice.security;

import br.com.authservice.user.UserService;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserService usersService;

    public ApplicationUserDetailsService(UserService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new CustomUserDetails(usersService.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username Not Found")));
    }
}
