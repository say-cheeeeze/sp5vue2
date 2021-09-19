package app.messages;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 스프링 컨테이너 인스턴스화 하는데 사용할 설정 메타 데이터
 */
@Configuration
@ComponentScan("app.messages")
public class AppConfig {

//		@Bean
//		public MessageRepository messageRepository() {
//				return new MessageRepository();
//		}
//		@Bean
//		public MessageService messageService() {
//				return new MessageService( messageRepository() );
//		}
		@Bean
		public FilterRegistrationBean<AuditingFilter> auditingFilterFilterRegistrationBean() {
				FilterRegistrationBean<AuditingFilter> regist = new FilterRegistrationBean<>();
				AuditingFilter filter = new AuditingFilter();
				regist.setFilter( filter );
				regist.setOrder( Integer.MAX_VALUE );
				regist.setUrlPatterns( Arrays.asList( "/messages/*" ) );
				return regist;
		}

}
