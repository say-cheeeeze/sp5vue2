package app.messages;


import javax.persistence.*;
import java.util.Date;

/**
 * Message 객체
 * -> spring-orm 라이브러리는 하이버네이트와 같은 ORM 기술을 기반으로 하는 스프링의 ORM 을 지원한다.
 * -> hibernate-core 라이브러리는 하이버네이트 ORM 프레임워크이다.
 *
 * 	Message 객체와 messages 테이블의 레코드를 매핑하는 방법을 알 수 있도록
 * 	메타 데이터를 제공해줘야한다. JPA 어노테이션을 사용한다.
 */
@Entity
@Table( name="messages" )
public class Message {
		
		// identity 전략을 사용한다. 이것은 id 컬럼의
		// Auto_increment 설정과 완전히 동일하다.
		@Id
		@GeneratedValue( strategy = GenerationType.IDENTITY )
		@Column( name = "id", nullable = false )
		private Integer id;
		
		@Column( name = "text", nullable = true, length = 128 )
		private String text;
		
		// Temporal 어노테이션은
		// java.util.Date 또는 java.util.Calendar 타입의
		// 필드에 필수적으로 추가해야 한다.
		@Column( name = "input_date", nullable = false )
		@Temporal( TemporalType.TIMESTAMP )
		private Date inputDate;
		
		public Message() {}	// 하이버네이트는 기본적으로 public 생성자가 필요하다. 하이버네이트가 DB 에서
							// 테이블 레코드를 가져올 때 Message 객체를 재구성하는데 이 때 객체를 생성하기 위해 기본 생성자를 사용한다.
		
		public Message( String text ) {
				this.text = text;
				this.inputDate = new Date();
		}
		public Message( int id, String text, Date inputDate ) {
				this.id = id;
				this.text = text;
				this.inputDate = inputDate;
		}
		public String getText() {
				return text;
		}
		public Integer getId() {
				return id;
		}
		public Date getInputDate() {
				return inputDate;
		}
		
}
