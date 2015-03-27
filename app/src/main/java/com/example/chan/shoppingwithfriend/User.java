package com.example.chan.shoppingwithfriend;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chan on 2/9/2015.
 */
public class User {

    static Map<String, String> database = new HashMap<String, String>();
    static Map<String, Set<String>> friendDatabase = new HashMap<String, Set<String>>();
    static Map<String, Map<String, String>> itemDatabase = new HashMap<String, Map<String, String>>();

    //dummyList<String> list = new ArrayList<String>(friendSet);
    public static Map<String, Set<String>> getFriendDatabase() {
        return friendDatabase;
    }

    public static void setItemDatabase(Map<String, Map<String, String>> ItemDatabase) {
        itemDatabase = ItemDatabase;
    }

    public static Map<String, Map<String, String>> getItemDatabase() {
        return itemDatabase;
    }

    /**
     * Getter method for database
     * @return database containing user information.
     */
    public Map<String, String> getDatabase() {
        return database;
    }

    /**
     * setter method for database
     * @param database that is unique per person.
     */
    public void setDatabase(Map<String, String> database) {
        this.database = database;
    }

    /**
     * Constructor for User class
     */
    public User(){
        String username;
        String password;
        Map<String, String> wishlist;
    }
}