package app.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Message Service API 제공
 */
@Component
public class MessageService {
		
		private static final Logger log = LoggerFactory.getLogger( MessageService.class );
		
		private MessageRepository repository;
		
		public MessageService( MessageRepository repository ) {
				this.repository = repository;
		}
		
		@SecurityCheck
		@Transactional(noRollbackFor = { UnsupportedOperationException.class })
		public Message save( String text ) {
				Message message = repository.saveMessage( new Message( text ) );
				log.debug("New Message [ id={} ] saved ", message.getId() );
				updateStatistics();
				
				return message;
		}
		
		private void updateStatistics() {
				throw new UnsupportedOperationException("This method is not implemented yet");
		}
}
