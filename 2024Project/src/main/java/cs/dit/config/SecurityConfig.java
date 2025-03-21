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
                .requestMatchers("/", "/board/**", "/resources/**").permitAll() // ✅ 모든 사용자 접근 허용
                .anyRequest().authenticated() // ✅ 그 외 요청은 인증 필요
            )
            .csrf(csrf -> csrf.disable()) // ✅ CSRF 보호 비활성화 (필요 시 활성화)
            .formLogin(login -> login
                .loginPage("/board/login") // ✅ 사용자 로그인 페이지 설정
                .defaultSuccessUrl("/board/index", true) // ✅ 로그인 성공 후 이동할 페이지
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/board/logout") // ✅ 로그아웃 URL
                .logoutSuccessUrl("/board/index") // ✅ 로그아웃 성공 후 이동할 페이지
                .permitAll()
            );

        return http.build();
    }

    // ✅ BCrypt 비밀번호 인코더 설정
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
 