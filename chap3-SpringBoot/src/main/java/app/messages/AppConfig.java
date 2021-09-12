package app.messages;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 스프링이 컨테이너를 인스턴스화하는데 사용할 설정 메타 데이터
@Configuration
@ComponentScan("app.messages")
public class AppConfig {

    @Bean
    public FilterRegistrationBean<AuditingFilter> auditingFilterRegistrationBean() {

        FilterRegistrationBean<AuditingFilter> registration = new FilterRegistrationBean<>();
        AuditingFilter filter = new AuditingFilter();

        registration.setFilter( filter );
        registration.setOrder(Integer.MAX_VALUE);
        registration.setUrlPatterns(Arrays.asList("/messages/*"));
        return registration;
    }

}
