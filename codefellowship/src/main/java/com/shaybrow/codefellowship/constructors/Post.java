package com.shaybrow.codefellowship.constructors;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    @ManyToOne
    public CodefellowshipUser author;
    @Column(columnDefinition = "TEXT")
    public String post;

    public Post (){}
    public Post(CodefellowshipUser author, String post) {
        this.author = author;
        this.post = post;
    }

}
