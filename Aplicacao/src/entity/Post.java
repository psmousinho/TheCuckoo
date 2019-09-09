package entity;

public class Post {
    private final UserProfile author;
    private final String dateStamp;
    private final String text;
    private final String photo;
    
    public Post(UserProfile author, String dateStamp, String text, String photo) {
        this.author = author;
        this.dateStamp = dateStamp;
        this.text = text;
        this.photo = photo;
    }
    
    public Post(UserProfile author, String dateStamp, String text) {
        this.author = author;
        this.dateStamp = dateStamp;
        this.text = text;
        
        photo = "";
    }
    
    public UserProfile getAuthor() {
        return author;
    }
    
    public String getDate() {
        return dateStamp;
    }
    
    public String getText() {
        return text;
    }
    
    public String getPhoto() {
        return photo;
    }
}
