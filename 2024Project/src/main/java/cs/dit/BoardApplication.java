package cs.dit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@ServletComponentScan  // ✅ 서블릿, 필터, 리스너 자동 감지
@EnableWebMvc  // ✅ Spring MVC 활성화
public class BoardApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        System.out.println("✅ BoardApplication 실행됨!");
        SpringApplication.run(BoardApplication.class, args);
    }

    // ✅ 뷰 리졸버 설정 (기존 servlet-context.xml에서 설정했던 것과 동일)
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    // ✅ 정적 리소스 핸들링 (기존 servlet-context.xml에서 설정했던 것과 동일)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}
