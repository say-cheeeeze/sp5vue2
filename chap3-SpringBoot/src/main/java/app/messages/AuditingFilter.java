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
