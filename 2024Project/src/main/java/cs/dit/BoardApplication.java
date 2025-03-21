package cs.dit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) // 🔥 Spring Security 기본 설정 제거
@ServletComponentScan  // ✅ 서블릿, 필터, 리스너 자동 감지
@ComponentScan(basePackages = {"cs.dit.controller", "cs.dit.service", "cs.dit.mapper", "cs.dit.config", "cs.dit.domain"})  // ✅ 컨트롤러 강제 스캔
public class BoardApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.out.println("✅ BoardApplication 실행됨!");
        SpringApplication.run(BoardApplication.class, args);
    }

    // ✅ JSP 뷰 리졸버 설정 (JSP 파일을 찾을 수 있도록 설정)
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}

// ✅ DispatcherServlet 설정 추가 (이 코드가 있어야 DispatcherServletPath 오류 해결 가능)
@Configuration
class ServletConfig {
    @Bean
    public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>(dispatcherServlet);
        registration.setLoadOnStartup(1);
        return registration;
    }
} 
