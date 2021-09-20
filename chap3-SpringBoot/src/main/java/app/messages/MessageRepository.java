package app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

/**
 * 메시지 저장 담당 DAO 역할
 */
@Component
public class MessageRepository {
		
		private final static Log log = LogFactory.getLog( MessageRepository.class );
		
		private DataSource dataSource;
		
		public MessageRepository(DataSource dataSource ) {
				this.dataSource = dataSource;
		}

		public Message saveMessage( Message message ) {
				log.info("Saved Message : " + message.getText() );
				
				Connection con = DataSourceUtils.getConnection( dataSource );
				try {
						log.info( message.getText() );
						log.info( message.getInputDate() );
						String insertSQL = "INSERT INTO MESSAGES (`ID`, `TEXT`, `INPUT_DATE`) value ( null, ?, ? )";
						PreparedStatement ps = con.prepareStatement( insertSQL, Statement.RETURN_GENERATED_KEYS );
						ps.setString(1, message.getText() );
						ps.setTimestamp( 2, new Timestamp( message.getInputDate().getTime() ) );
						int rowAffected = ps.executeUpdate();
						
						if ( rowAffected > 0 ) {
								ResultSet result = ps.getGeneratedKeys();
								if ( result.next() ) {
										int id = result.getInt( 1 );
										return new Message( id, message.getText(), message.getInputDate() );
								}
								else {
										log.error("Failed to retrieve id. No row in result set");
										return null;
								}
						}
						else {
								return null;
						}
				}
				catch( SQLException ex ) {
					ex.printStackTrace();
					log.error("Failed to save message", ex );
					try {
							con.close();
					}
					catch( SQLException e ) {
							e.printStackTrace();
							log.error("Filaed to Close Connection", e );
					}
				}
				finally {
						DataSourceUtils.releaseConnection( con, dataSource );
				}
				return null;
				
		}
}
