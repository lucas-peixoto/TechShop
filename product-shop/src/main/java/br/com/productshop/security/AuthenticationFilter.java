package br.com.productshop.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isMissingAuthorizationHeader(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String email = request.getHeader("email");
        String role = request.getHeader("role");

        tryToAuthenticate(email, role);

        filterChain.doFilter(request, response);
    }

    private boolean isMissingAuthorizationHeader(HttpServletRequest request) {
        return request.getHeader("email") == null
                || request.getHeader("email").isBlank()
                || request.getHeader("role") == null
                || request.getHeader("role").isBlank();
    }

    private void tryToAuthenticate(String email, String role) {
        User userDetails = new User(email, "", true, true, true, true, AuthorityUtils.createAuthorityList(role));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
    }
}
