필터 ( Filter )

필터는 자바EE의 또 다른 좋은 기술이다.
이 필터는 디자인 패턴 중 책임 연쇄 ( Chain of Responsibility)
을 구현한 것이다.

이것은 서블릿에 도달하기 전에 HTTP 요청에 대한 필터링 작업을 수행하려고 할 때 유용하다.

Audit( 감사 ) 요청에 대한 AuditingFilter 를 만들어보자.

필터를 만드려면 javax.servlet.Filter 인터페이스를 구현해야한다.
또는 스프링의 org.springframework.web.filter.GenericFilterBean 을 확장해서 필터를 만들 수도 있다.
GenericFilterBean 을 사용해보자.

AuditingFilter.java

```
package app.messages;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

public class AuditingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        // chaing.doFilter 를 호출하기 전에 시간을 기록한다.
        long start = new Date().getTime();

        // 체인에 실행할 필터가 존재하면 추가 필터를 호출할 수 있도록 한다.
        chain.doFilter( req, res );

        long elapsed = new Date().getTime() - start;
                    
        HttpServletRequest request = (HttpServletRequest) req;

        
        logger.debug("Request[uri = " + request.getRequestURI() 
        + ", method =" + request.getMethod() 
        + "] completed in " + elapsed + "ms");
    }
    
    
}

```

그리고 AppConfig.java 파일을 수정한다.
```
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

```

auditingFilterRegistrationBean 메소드에 Bean 어노테이션을 적용하고

AuditingFilter 에 대한 FilterRegistrationBean 을 생성한다.

setUrlPatterns() 메소드는 Filter 를 등록할 구체적인 경로를 지정한다.

AuditingFilter 는 경로가 /messages/ 로 시작하는 요청만 처리하기로 한다.

그리고 출력 결과에 디버그 로그를 표시하려면 AuditingFilter 에 대한 디버그 레벨 로그를 설정해야한다.

이를 위해 /src/main/resources/ 디렉토리 아래에 application.properties 파일을 생성하고

여기에 AuditingFilter 의 기본 로깅 레벨을 재정의할 수 있다.

---

src/main/resources/application.properties 파일
```
logging.level.app.messages.AuditingFilter=DEBUG
```
---
애플리케이션을 재시작하고 페이지를 새로고치면 (/messages url에 해당하는 페이지를)

로그에 디버그 정보를 확인할 수 있다.

```

2021-09-12 17:34:34.741 DEBUG 3796 --- [nio-8080-exec-1] app.messages.AuditingFilter              : Request[uri = /messages/welcome, method =GET] completed in 191ms
```

