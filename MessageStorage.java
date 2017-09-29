import java.util.Arrays;

public class MessageStorage {

    private AbstractMessage[] messages;

    public MessageStorage() {
        messages = new AbstractMessage[0];
    }

    public void add(AbstractMessage message){

        AbstractMessage[] tmpMessages = new AbstractMessage[messages.length + 1];
        System.arraycopy(messages, 0, tmpMessages, 0, messages.length);
        tmpMessages[messages.length] = message;
        messages = tmpMessages;

    }

    public AbstractMessage[] getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return Arrays.toString(messages);
    }
}
