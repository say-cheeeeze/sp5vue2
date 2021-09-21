package app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

/**
 * 메시지 저장 담당 DAO 역할
 *
 * =======================================================
 *
 * 	21.	09.	21		하이버네이트로 메시지 객체를 저장하는 것으로 변경 작업 ( 기존 소스주석처리됨 )
 *					org.hibernate.SessionFactory 추가
 *					스프링에 SessionFactory 의 인스턴스를 추가한 다음, saveMessage 메소드에서
 *					sessionFactory 인스턴스의 opnSession 메소드를 호출해 인스턴스를 획득하고,
 *					message 객체를 저장하는 session 객체의 save 메소드를 사용한다.
 *
 *					message 객체의 생성된 id 를 얻는 것에 대해 걱정할 필요가 없다. 하이버네이트가 관리한다.
 */
@Component
public class MessageRepository {
		
		private final static Log log = LogFactory.getLog( MessageRepository.class );
		
//		private NamedParameterJdbcTemplate jdbcTemplate;
		
		private SessionFactory sessionFactory;
		
//		@Autowired
//		public void setDataSource( DataSource dataSource ) {
//				this.jdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
//		}

//		private DataSource dataSource;
		
		public MessageRepository(SessionFactory sessionFactory ) {
				this.sessionFactory = sessionFactory;
		}

		public Message saveMessage( Message message ) {
				
				// hibernate Session
				Session session = sessionFactory.openSession();
				session.save( message );
				return message;
				
//				log.info(" Trying to save Message : " + message.getText() );
//
//				GeneratedKeyHolder holder = new GeneratedKeyHolder();
//				MapSqlParameterSource params = new MapSqlParameterSource();
//				params.addValue( "text", message.getText() );
//				params.addValue( "inputDate", message.getInputDate() );
//
//				String insertSQL = "INSERT INTO MESSAGES (`ID`, `TEXT`, `INPUT_DATE`) value ( null, :text, :inputDate )";
//
//				try {
//						log.info( message.getText() );
//						log.info( message.getInputDate() );
//						this.jdbcTemplate.update( insertSQL, params, holder );
//				}
//				catch( DataAccessException ex ) {
//					ex.printStackTrace();
//					log.error("Failed to save message", ex );
//					return null;
//				}
//				return new Message( holder.getKey().intValue(), message.getText(), message.getInputDate() );
		}
}
