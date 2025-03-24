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
            // 🔓 특정 경로는 인증 없이 접근 허용
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/board/**", "/resources/**").permitAll()
                .anyRequest().authenticated()
            )
            // 🚫 CSRF 비활성화 (폼 사용 시 필요에 따라 활성화 가능)
            .csrf(csrf -> csrf.disable())
            // 🔐 로그인 설정
            .formLogin(login -> login
                .loginPage("/board/login")
                .defaultSuccessUrl("/board/index", true)
                .permitAll()
            )
            // 🔐 로그아웃 설정
            .logout(logout -> logout
                .logoutUrl("/board/logout")
                .logoutSuccessUrl("/board/index")
                .permitAll()
            );

        return http.build();
    }

    // 🔐 비밀번호 암호화를 위한 BCrypt 인코더 Bean 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
