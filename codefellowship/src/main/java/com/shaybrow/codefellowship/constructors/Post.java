package com.shaybrow.codefellowship.constructors;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    @OneToOne
    String username;
    @Lob
    String post;

    public Post (){}
    public Post(String username, String post) {
        this.username = username;
        this.post = post;
    }

}
