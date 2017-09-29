import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractMessage {

    protected int messageId;
    protected String text;
    protected User author;
    protected Date date;

    public abstract void send(MessageStorage messageStorage);



    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Message: messageId = " + messageId + ", text = " + text + ", author: " + author + ", message date = " + dateFormat.format(date);
    }
}
