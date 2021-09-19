package app.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Message Service API 제공
 */
@Component
public class MessageService {
		
		private MessageRepository repository;
		
		@Autowired
		public MessageService( MessageRepository repository ) {
				this.repository = repository;
		}
		public void save( String text ) {
				repository.saveMessage( new Message( text ) );
		}
}
