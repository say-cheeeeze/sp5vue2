package app.messages;

import java.util.Date;

/**
 * 메시지 객체
 */
public class Message {
    
    private String text;
    private int id;
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

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public Date getInputDate() {
        return inputDate;
    }
}
