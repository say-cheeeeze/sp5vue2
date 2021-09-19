package app.messages;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 스프링 컨테이너 인스턴스화 하는데 사용할 설정 메타 데이터
 */
@Configuration
@ComponentScan("app.messages")
public class AppConfig {
		
		@Bean
		public MessageRepository messageRepository() {
				return new MessageRepository();
		}
		@Bean
		public MessageService messageService() {
				return new MessageService( messageRepository() );
		}

}
