package app.messages;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 메시지 저장 작업
 * 
 * 9.12.    hibernate 도입 구조 변경
 */
@Component
public class MessageRepository {

  private SessionFactory sessionFactory;

  public MessageRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Message saveMessage(Message message) {
    Session session = sessionFactory.openSession();
    session.save(message);
    return message;
  }
}
