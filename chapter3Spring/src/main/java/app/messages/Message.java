package app.messages;

/**
 * 메시지 객체
 */
public class Message {
    
    private String text;
    public Message( String text ) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
