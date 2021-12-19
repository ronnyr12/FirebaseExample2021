package com.example.firebaseexample2021;

public class Post {
    public String title;
    public String body;
    public String uid;
    public String key;

    public Post() {
        //by default required by firebase an empty constructor
    }

    /**
     * @param title
     * @param body
     * @param uid
     * @param key
     */
    public Post(String title, String body, String uid, String key) {
        this.title = title;
        this.body = body;
        this.uid = uid;
        this.key = key;
    }
}
