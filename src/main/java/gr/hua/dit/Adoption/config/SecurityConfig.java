package gr.hua.dit.Adoption.config;

import gr.hua.dit.Adoption.handlers.CustomAuthenticationFailureHandler;
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

    private CustomAuthenticationFailureHandler failureHandler;


    public SecurityConfig(UserService userService,UserDetailsService userDetailsService,BCryptPasswordEncoder passwordEncoder,CustomAuthenticationFailureHandler failureHandler) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.failureHandler = failureHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/users", "/admin/shelters/approve/**", "/admin/shelters/reject/**" , "/auth/pending-shelters" , "/admin/animals/approve/**" , "/auth/pending-animals").hasRole("ADMIN")
                                .requestMatchers("/shelter/**" , "/animal/add-animal" , "/shelter/animals/approve/{animal_id}" , "/auth/adoption-application" , "/shelter/animals/reject/{animal_id}").hasRole("SHELTER")
                                .requestMatchers("/animal/animals/**").hasRole("ADOPTER")
                                .requestMatchers("/vet/animals/approve/**" , "/auth/pending-animals-health").hasRole("VET")
                                .requestMatchers("/", "/home","/register", "saveUser", "/saveVet" ,  "/saveShelter","/saveAnimal",  "/register/vet" , "/register/shelter" , "/register/shelter" , "/images/**", "/js/**", "/css/**", "/actuator/health/**").permitAll()

//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/adopter/**").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureHandler(failureHandler)
                        .permitAll())
                .logout((logout) -> logout.permitAll())
                .userDetailsService(userDetailsService);

        return http.build();
    }

}
