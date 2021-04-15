package com.shaybrow.codefellowship.controllers;

import com.shaybrow.codefellowship.constructors.CodefellowshipUser;
import com.shaybrow.codefellowship.constructors.Post;
import com.shaybrow.codefellowship.repos.CodefellowshipUserRepo;
import com.shaybrow.codefellowship.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    CodefellowshipUserRepo codefellowshipUserRepo;

    @Autowired
    PostRepo postRepo;

    @PostMapping("/post")
    public RedirectView addPost (String username, String post, Principal p){
        CodefellowshipUser poster = codefellowshipUserRepo.findByUsername(p.getName());
        Post post1 = new Post(poster, post);
        post1.author = poster;
        postRepo.save(post1);

        return new RedirectView("/myprofile");

    }

}
