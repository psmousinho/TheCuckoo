/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Pablo Suria
 */
public class NotificationTagPost {

    private UserProfile author;
    private String date;
    private String tagUser;

    public NotificationTagPost(UserProfile author, String date, String tagUser) {
        this.author = author;
        this.date = date;
        this.tagUser = tagUser;
    }

    public UserProfile getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getTagUser() {
        return tagUser;
    }

}
