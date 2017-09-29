import java.text.SimpleDateFormat;
import java.util.Date;


public class Post {

    private long postId;
    private String title;
    private String text;
    private Date postedAt;

    public Post(){
        postId = 0;
        title = "Пост по умолчанию";
        text = "Текст по умолчанию";
        postedAt = new Date();
    }

    public Post(long postId, String title, String text, Date postedAt){
        this.postId = postId;
        this.title = title;
        this.text = text;
        this.postedAt = postedAt;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Post: postId = " + postId + ", title = " + title + ", text = " + text + ", postedAt = " + dateFormat.format(postedAt);
    }
}
