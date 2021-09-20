package app.messages;

import org.springframework.stereotype.Component;

/**
 * Message Service API 제공
 */
@Component
public class MessageService {
		
		private MessageRepository repository;
		
		public MessageService( MessageRepository repository ) {
				this.repository = repository;
		}
		
		public Message save( String text ) {
				return repository.saveMessage( new Message( text ) );
		}
}
