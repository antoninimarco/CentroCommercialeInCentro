package it.unicam.cs.ids.c3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user").hasAnyRole("USER")
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .and().formLogin().loginPage("/login")
                .successHandler(new UrlAuthenticationSuccessHandler())
                .permitAll()
                .and().logout();

    }



    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails commercianteUser =
                User.withUsername("Mario Rossi")
                        .password("{noop}password")
                        .roles("COMMERCIANTE")
                        .build();
        UserDetails clienteUser =
                User.withUsername("Luigino Bianchi")
                        .password("{noop}password")
                        .roles("CLIENTE")
                        .build();

        UserDetails corriereUser =
                User.withUsername("Luca Verdi")
                        .password("{noop}password")
                        .roles("CORRIERE")
                        .build();
        return new InMemoryUserDetailsManager(commercianteUser,clienteUser,corriereUser);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/VAADIN/**",
                "/favicon.ico",
                "/robots.txt",
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.html",
                "/icons/**",
                "/images/**",
                "/styles/**",
                "/h2-console/**");
    }
}
