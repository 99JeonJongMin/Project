package cs.dit.config;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<DispatcherServlet> dispatcherServlet(DispatcherServlet servlet) {
        System.out.println("✅ DispatcherServlet 등록됨!");
        ServletRegistrationBean<DispatcherServlet> registrationBean = new ServletRegistrationBean<>(servlet, "/");
        registrationBean.setLoadOnStartup(1);  // 서버 시작 시 로드
        return registrationBean;
    }
    
    @Bean
    public DispatcherServletPath dispatcherServletPath() {
        return () -> "/";
    }
}
