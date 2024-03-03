package br.com.paymentservice.config;

import br.com.paymentservice.user.LoggedUser;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {

    private final LoggedUser loggedUser;

    public OpenFeignConfig(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            loggedUser.getLoggedUser().ifPresent(user -> {
                requestTemplate.header("email", user.getUsername());
                requestTemplate.header("role", user.getAuthorities().stream().toList().getFirst().toString());
            });
        };
    }
}
