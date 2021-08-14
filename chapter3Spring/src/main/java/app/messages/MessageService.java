package app.messages;

import org.springframework.stereotype.Component;

@Component
public class MessageService {

    // MessageRepository 를 MessageService의 의존성으로 정의한다.
    private MessageRepository repository;

    // MessageRepository 인스턴스를 매개변수로 갖는 MessageService 생성자를 생성한다.
    // 그리고 스프링은 이 생성자를 통해 의존성을 연결한다.
    public MessageService( MessageRepository repository ) {
        this.repository = repository;
    }

    public void save( String text ) {
        this.repository.saveMessage( new Message( text ) );
    }
    
}
