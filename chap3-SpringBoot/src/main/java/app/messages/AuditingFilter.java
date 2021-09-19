package app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class AuditingFilter extends GenericFilterBean {
		
		private final static Log log = LogFactory.getLog( AuditingFilter.class );
		
		@Override public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain )
					throws IOException, ServletException {
				
				long startTime = new Date().getTime();
				log.info("startTime ====================");
				log.info( startTime );
				chain.doFilter( req,res );
				long elapsedTime = new Date().getTime() - startTime;
				log.info("EndTime ====================");
				log.info( elapsedTime );
				
				HttpServletRequest request = (HttpServletRequest) req;
				log.debug("Request URI =" + request.getRequestURI()
							  + ", method=" + request.getMethod() +
							 "completed in " + elapsedTime + "ms");
				
		}
}
