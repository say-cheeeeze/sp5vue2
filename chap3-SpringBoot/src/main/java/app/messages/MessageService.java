package app.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

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
