package app.messages;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 스프링 컨테이너 인스턴스화 하는데 사용할 설정 메타 데이터
 */
@Configuration
@ComponentScan("app.messages")
public class AppConfig {
		
		private DataSource dataSource;
		
		public AppConfig( DataSource dataSource ) {
				this.dataSource = dataSource;
		}
		
		/**
		 * 하이버네이트에서 org.hibernate.session 은 엔티티를 저장하고 불러오기 위한 주요 인터페이스이다.
		 * 하이버네이트 SessionFactory 인스턴스에서 세션을 생성할 수 있다.
		 * 스프링 ORM 으로 sessionFactory 인스턴스를 생성하려면
		 * 스프링 FactoryBean 인 스프링의 LocalSessionFactoryBean 을 사용하면 된다.
		 *
		 * 일단 LocalSessionFactoryBean 을 생성하려면 javax.sql.DataSource 인스턴스가 필요하다.
		 * 따라서 스프링에서 설정 클래스로 주입해주도록 요청하고,
		 * setPackagesToScan 메소드를 통해 Entity 클래스를 검색할 패키지를 지정한다.
		 */
		@Bean
		public LocalSessionFactoryBean sessionFactory() {
			LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
			sessionFactoryBean.setDataSource( dataSource );
			sessionFactoryBean.setPackagesToScan( "app.messages" );
			return sessionFactoryBean;
		}
		
		
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
