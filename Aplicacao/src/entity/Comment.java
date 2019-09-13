package entity;

public class Comment {
    
    private UserProfile author;
    private String postAuthor;
    private String postDate;
    private String date;
    private String text;

    public Comment(UserProfile author, String postAuthor, String postDate, String date, String text) {
        this.author = author;
        this.postAuthor = postAuthor;
        this.postDate = postDate;
        this.date = date;
        this.text = text;
    }

    public UserProfile getAuthor() {
        return author;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
    
    
    
}
