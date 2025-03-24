package cs.dit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                "/", 
	                "/board/login", 
	                "/board/logout", 
	                "/board/index", 
	                "/board/**", 
	                "/resources/**",
	                "/test/open"
	            ).permitAll()
	            .anyRequest().authenticated()
	        )
	        .csrf(csrf -> csrf.disable())
	        .formLogin(login -> login
	            .loginPage("/board/login")
	            .defaultSuccessUrl("/board/index", true)
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutUrl("/board/logout")
	            .logoutSuccessUrl("/board/index")
	            .permitAll()
	        );
	    System.out.println("✅ Security filter chain 설정됨");


	    return http.build();
	}

    // 🔐 비밀번호 암호화를 위한 BCrypt 인코더 Bean 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
