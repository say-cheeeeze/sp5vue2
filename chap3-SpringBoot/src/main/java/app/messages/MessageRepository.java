package app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
/**
 * 메시지 저장 작업
 */
@Component
public class MessageRepository {

    private final static Log log = LogFactory.getLog( MessageRepository.class );

    public void saveMessage( Message message ) {
        // 데이터 베이스에 메시지를 저장한다.
        log.info( "saved message : " + message.getText() );
    }
    
}
