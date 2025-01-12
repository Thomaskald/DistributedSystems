package gr.hua.dit.Adoption.config;

import gr.hua.dit.Adoption.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private UserService userService;

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder passwordEncoder;


    public SecurityConfig(UserService userService,UserDetailsService userDetailsService,BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/users").hasRole("ADMIN")
                                .requestMatchers("/shelter/**" , "/animal/add-animal").hasRole("SHELTER")
                                .requestMatchers("/animal/animals").hasRole("ADOPTER")
                                .requestMatchers("/", "/home","/register", "saveUser", "/saveVet" ,  "/saveShelter","/saveAnimal",  "/register/vet" , "/register/shelter" , "/register/shelter" , "/images/**", "/js/**", "/css/**").permitAll()

//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/adopter/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

}
