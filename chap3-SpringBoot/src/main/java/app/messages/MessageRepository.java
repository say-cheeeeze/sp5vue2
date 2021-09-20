package app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 메시지 저장 담당 DAO 역할
 */
@Component
public class MessageRepository {
		
		private final static Log log = LogFactory.getLog( MessageRepository.class );
		
		private NamedParameterJdbcTemplate jdbcTemplate;
		
		@Autowired
		public void setDataSource( DataSource dataSource ) {
				this.jdbcTemplate = new NamedParameterJdbcTemplate( dataSource );
		}
		
		private DataSource dataSource;
		
		public MessageRepository(DataSource dataSource ) {
				this.dataSource = dataSource;
		}

		public Message saveMessage( Message message ) {
				
				log.info(" Trying to save Message : " + message.getText() );
				
				GeneratedKeyHolder holder = new GeneratedKeyHolder();
				MapSqlParameterSource params = new MapSqlParameterSource();
				params.addValue( "text", message.getText() );
				params.addValue( "inputDate", message.getInputDate() );
				
				String insertSQL = "INSERT INTO MESSAGES (`ID`, `TEXT`, `INPUT_DATE`) value ( null, :text, :inputDate )";
				
				try {
						log.info( message.getText() );
						log.info( message.getInputDate() );
						this.jdbcTemplate.update( insertSQL, params, holder );
				}
				catch( DataAccessException ex ) {
					ex.printStackTrace();
					log.error("Failed to save message", ex );
					return null;
				}
				return new Message( holder.getKey().intValue(), message.getText(), message.getInputDate() );
		}
}
