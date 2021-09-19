package app.messages;

import org.springframework.stereotype.Component;

/**
 * Message Service API 제공
 */
@Component
public class MessageService {
		
		private MessageRepository messageRepository;
		
		public MessageService( MessageRepository repository ) {
				this.messageRepository = repository;
		}
		public void save( String text ) {
				messageRepository.saveMessage( new Message( text ) );
		}
}
