package app.messages;

/**
 * 메시지 전송할때 사용하는 객체
 *
 * POJO ( Plain Old Java Object )
 * 하나의 text 필드만 있는 클래스이다.
 */
public class MessageData {
		
		private String text;
		public String getText() {
				return this.text;
		}
		public void setText( String text ) {
				this.text = text;
		}
}
