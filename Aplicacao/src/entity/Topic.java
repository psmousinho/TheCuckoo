package entity;

public class Topic {
    private final String name;
    private String date;
    
    public Topic(String name, String date) {
        this.name = name;
        this.date = date;
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
}
