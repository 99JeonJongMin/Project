package cs.dit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll() // 🔥 모든 경로 접근 허용
            )
            .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (필요에 따라)
            .formLogin(login -> login.disable()) // 🔥 로그인 폼 비활성화
            .logout(logout -> logout.logoutUrl("/board/logout").permitAll());

        return http.build();
    }
}
