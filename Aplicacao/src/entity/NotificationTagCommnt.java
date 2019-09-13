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
public class NotificationTagCommnt extends NotificationTagPost {

    private UserProfile postAuthor;
    private String postDate;

    public NotificationTagCommnt(UserProfile postAuthor, String postDate, UserProfile author, String date, String tagUser) {
        super(author, date, tagUser);
        this.postAuthor = postAuthor;
        this.postDate = postDate;
    }

    public UserProfile getPostAuthor() {
        return postAuthor;
    }

    public String getPostDate() {
        return postDate;
    }

}
