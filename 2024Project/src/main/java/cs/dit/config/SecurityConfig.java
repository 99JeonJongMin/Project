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
                    "/board/memreg",        // 회원가입 페이지
                    "/board/**",            // board 컨트롤러의 기타 URL들
                    "/resources/**"         // 정적 리소스 (css, js 등)
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

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
