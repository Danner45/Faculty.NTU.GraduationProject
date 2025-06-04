package falcuty.ntu.groupone.graduation.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationSuccessHandler successHandler) throws Exception {
		return http
	            .headers(headers -> headers
	                    .frameOptions(frameOptions -> frameOptions
	                        .sameOrigin() // Cho phép nhúng trong iframe cùng origin
	                    )
	                )
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/login", "/css/**", "/image/**").permitAll()
                                .requestMatchers("/supervisors/**").hasRole("SUPERVISOR")
                                .requestMatchers("/student/**").hasRole("STUDENT")
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                				.loginPage("/login")
                				.loginProcessingUrl("/login")
                        		.successHandler(successHandler)
                        		.permitAll()
                )
                .logout(config -> config.logoutSuccessUrl("/login"))
                .build();
	}
}
