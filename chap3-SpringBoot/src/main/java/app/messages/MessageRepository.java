package app.messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
/**
 * 메시지 저장 작업
 */
@Component
public class MessageRepository {

    private DataSource dataSource;
    
    private final static Log log = LogFactory.getLog( MessageRepository.class );

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDateSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public MessageRepository( DataSource dataSource ) {
        this.dataSource = dataSource;
    }

    /**
     * 메시지를 저장합니다.
     * 기존 saveMessage 와 달리 SpringJDBC 를 이용합니다.
     * 
     * @author cheeeeze
     * @param message
     * @return
     */
    public Message saveMessage2( Message message ) {

        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("text", message.getText() );
        params.addValue("inputDate", message.getInputDate());
        String insertSQL = "INSERT INTO messages (`id`, `text`, `input_date` ) VALUES ( null , :text, :inputDate)";

        try {
            this.jdbcTemplate.update(insertSQL, params, holder );
            
        }
        catch(DataAccessException e) {
            log.error("Failed to save message", e );
            return null;
        }
        return new Message( holder.getKey().intValue(), message.getText(), message.getInputDate() );
    }

    public Message saveMessage( Message message ) {




        Connection conn = DataSourceUtils.getConnection(dataSource);

        try {
            String insertSQL = "INSERT INTO messages (`id`, `text`, `input_date` ) VALUES ( null , ?, ?)";
            PreparedStatement ps = conn.prepareStatement( insertSQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, message.getText() );
            ps.setTimestamp(2, new Timestamp(message.getInputDate().getTime()));
            int rowsAffected = ps.executeUpdate();
            
            if ( rowsAffected > 0 ) {
                ResultSet result = ps.getGeneratedKeys();
                if ( result.next()) {
                    int id = result.getInt(1);
                    log.info( "saved message : " + message.getText() );
                    log.info( "affected rows : " + rowsAffected );
                    return new Message( id, message.getText(), message.getInputDate());
                } 
                else {
                    log.error("failed to retrieve id, No row in result " );
                    return null;
                }
            }
            else {
                return null;
            }
        }
        catch( SQLException e) {
            log.error("failed to save message", e );

            try {
                conn.close();
            }
            catch( SQLException ex) {
                log.error("failed to close connection..", ex );
            }
            finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
            return null;
            
        }

        
    }
    
}
