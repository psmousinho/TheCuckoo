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
public class User {
    private String realName;
    
    public User(String realName) {
        
    }
    
    public static boolean validateUsername(String username) {
        String username_regex = "^\\w{4,32}$";
        Pattern pattern = Pattern.compile(username_regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
}
