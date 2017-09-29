import java.util.Date;

public class PostMessage extends AbstractMessage {

    private Post post;

    public PostMessage(int messageId, String text, User author, Date date, Post post) {
        super.messageId = messageId;
        super.text = text;
        super.author = author;
        super.date = date;
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public void send(MessageStorage messageStorage) {
        messageStorage.add(PostMessage.this);
    }

    @Override
    public String toString() {
        return "Post " + super.toString() + ", " + post;
    }
}
