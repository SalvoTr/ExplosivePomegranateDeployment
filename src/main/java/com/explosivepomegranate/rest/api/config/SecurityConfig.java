package com.explosivepomegranate.rest.api.config;

import com.explosivepomegranate.rest.api.security.CSRFRequestMatcher;
import com.explosivepomegranate.rest.api.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true, prePostEnabled = true) // enables the @Secured attribute for methods
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailService; //

    /**
     * Encypt password with BCryptPasswordEncoder with 12 rounds
     *
     * @author Clelia
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    /**
     * @author Clelia
     * */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
                .requiresChannel().requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null).requiresSecure()
                .and() // If the X-Forwarded-Proto header is present, redirect to HTTPS (Heroku)
                    .csrf().disable()
                   //.requireCsrfProtectionMatcher(new CSRFRequestMatcher())
                   // .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests()
                    .antMatchers("/assets/**", "/", "/register").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/")
                    .defaultSuccessUrl("/home")
                    .permitAll()
                .and().httpBasic().realmName("REALM_EXPLOSIVE").authenticationEntryPoint(entryPoint());
    }

    /**
     * Add entry point for postman to access with basic authentication configurations
     * @author Clelia
     * */
    public BasicAuthenticationEntryPoint entryPoint(){
        BasicAuthenticationEntryPoint basic = new BasicAuthenticationEntryPoint();
        basic.setRealmName("REALM_EXPLOSIVE");
        return basic;
    }
}
