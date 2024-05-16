package com.ufu.tcc.commonsdomain.config;

import com.ufu.tcc.commonsdomain.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                    (authorize) -> authorize
                            .requestMatchers("/user").hasRole(Role.MANAGER.name())
                            .requestMatchers("/user-login").permitAll()
                            .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;
    }


    @Bean
    public JdbcUserDetailsManager userDetailsService() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setCreateUserSql("INSERT INTO hotel_tcc.users (username, password, enabled) VALUES (?, ?, ?)");
        jdbcUserDetailsManager.setCreateAuthoritySql("INSERT INTO hotel_tcc.authorities (username, authority) VALUES (?, ?)");
        jdbcUserDetailsManager.setUserExistsSql("SELECT username FROM hotel_tcc.users WHERE username = ?");
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM hotel_tcc.users WHERE username = ?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM hotel_tcc.authorities WHERE username = ?");

        if (!jdbcUserDetailsManager.userExists("admin")) {
            UserDetails userDetails = User.builder()
                    .username("admin")
                    .password("{bcrypt}$2a$10$A28j4KJgFe7W2TU2Zd9lJesCLQfGv7GJkxp1KihEGpWfmH3FfitsO")
                    .roles(Role.MANAGER.name())
                    .build();

            jdbcUserDetailsManager.createUser(userDetails);
        }

        return jdbcUserDetailsManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder builder) {
        builder.eraseCredentials(false);
    }


}
