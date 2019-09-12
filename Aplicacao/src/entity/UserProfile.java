/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author aluno
 */
public class UserProfile {
    public static UserProfile CURRENT_USER;
    
    private String realName;
    private final String login;
    private String biography;
    private boolean visibility;
    private int numberFollowers;
    private int numberFollowing;
    
    public UserProfile(String realName, String login, String biography, boolean visibility, int numberFollowers, int numberFollowing) {
        this.realName = realName;
        this.login = login;
        this.biography = biography;
        this.visibility = visibility;
        this.numberFollowers = numberFollowers;
        this.numberFollowing = numberFollowing;
    }
    
    public static boolean validateUsername(String username) {
        String username_regex = "^\\w{4,32}$";
        Pattern pattern = Pattern.compile(username_regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    
    public String getName() {
        return realName;
    }
    
    public void setName(String newName) {
        realName = newName;
    }
    
    public String getUsername() {
        return login;
    }
    
    public String getBio() {
        return biography;
    }
    
    public void setBio(String newBio) {
        biography = newBio;
    }
    
    public boolean isPrivate() {
        return visibility;
    }
    
    public void setPrivate(boolean newVisibility) {
        visibility = newVisibility;
    }

    public int getNumberFollowers() {
        return numberFollowers;
    }

    public int getNumberFollowing() {
        return numberFollowing;
    }

    public void setNumberFollowers(int numberFollowers) {
        this.numberFollowers = numberFollowers;
    }

    public void setNumberFollowing(int numberFollowing) {
        this.numberFollowing = numberFollowing;
    }
    
    
}
