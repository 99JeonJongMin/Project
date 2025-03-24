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
                    "/board/login",       // 🔥 로그인 페이지 직접 추가!
                    "/board/logout",      // 🔥 로그아웃 URL도 명시적으로!
                    "/board/index",       // 🔥 로그인 후 리디렉션 경로도 명시!
                    "/board/**", 
                    "/resources/**"
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
