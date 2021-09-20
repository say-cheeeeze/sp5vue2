package app.messages;

import java.util.Date;

/**
 * Message 객체
 */
public class Message {
		
		private Integer id;
		private String text;
		private Date inputDate;
		
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
