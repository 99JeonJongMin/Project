package cs.dit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll() // ✅ 모든 경로 접근 허용
            )
            .csrf(csrf -> csrf.disable()) // ✅ CSRF 보호 비활성화 (필요에 따라)
            .formLogin(login -> login.disable()) // ✅ 기본 로그인 폼 비활성화
            .logout(logout -> logout
                .logoutUrl("/board/logout") // ✅ 로그아웃 URL 지정
                .logoutSuccessUrl("/board/index") // ✅ 로그아웃 후 리다이렉트할 경로
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // ✅ 비밀번호 해싱 기능 유지
    }
    
}
