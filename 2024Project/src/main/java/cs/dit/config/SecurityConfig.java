package cs.dit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
	    MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

	    http
	        .authorizeHttpRequests(auth -> auth
	        		.requestMatchers(
	        			    mvc.pattern("/"),
	        			    mvc.pattern("/board/index"),   // ✅ 반드시 명시적으로 추가!
	        			    mvc.pattern("/board/**"),
	        			    mvc.pattern("/resources/**")
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
