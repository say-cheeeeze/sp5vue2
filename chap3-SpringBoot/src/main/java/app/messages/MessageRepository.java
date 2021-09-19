package app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * 메시지 저장 담당 DAO 역할
 */
@Component
public class MessageRepository {
		
		private final static Log log = LogFactory.getLog( MessageRepository.class );

		public void saveMessage( Message message ) {
				log.info("Saved Message : " + message.getText() );
		}
}
