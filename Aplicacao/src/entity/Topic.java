package entity;

public class Topic {
    private final String name;
    private String date;
    private int nPosts;
    private int nComments;
    
    public Topic(String name, String date, int nPosts, int nComments) {
        this.name = name;
        this.date = date;
        this.nPosts = nPosts;
        this.nComments = nComments;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String newDate) {
        date = newDate;
    }
    
    public int getNumberPosts() {
        return nPosts;
    }

    public void setNumberPosts(int number) {
        nPosts = number;
    }
    
    public int getNumberComments() {
        return nComments;
    }
    
    public void setNumberComments(int number) {
        nComments = number;
    }
}
