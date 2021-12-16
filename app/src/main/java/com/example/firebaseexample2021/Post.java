package com.example.firebaseexample2021;

public class Post {
    String title;
    String body;
    String uid;
    String key;

    public Post(){
        //by default required by firebase an empty constructor
    }

    public Post(String title, String body, String uid, String key) {
        this.title = title;
        this.body = body;
        this.uid = uid;
        this.key = key;
    }
}
