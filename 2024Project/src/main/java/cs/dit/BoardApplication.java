package cs.dit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) // 🔥 Spring Security 기본 설정 제거
@ServletComponentScan  // ✅ 서블릿, 필터, 리스너 자동 감지
public class BoardApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.out.println("✅ BoardApplication 실행됨!");
        SpringApplication.run(BoardApplication.class, args);
    }

    // ✅ JSP 뷰 리졸버 설정
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public DispatcherServletPath dispatcherServletPath() {
        return () -> "/";
    }
}
