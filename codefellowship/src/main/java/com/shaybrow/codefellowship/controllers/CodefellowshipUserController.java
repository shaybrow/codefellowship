package com.shaybrow.codefellowship.controllers;

import com.shaybrow.codefellowship.codefellowshipUsers.CodefellowshipUser;
import com.shaybrow.codefellowship.repos.CodefellowshipUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class CodefellowshipUserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CodefellowshipUserRepo codefellowshipUserRepo;

    @PostMapping("/codeUser")
    public RedirectView createUser(String username, String password){
        password = passwordEncoder.encode(password);

        CodefellowshipUser user = new CodefellowshipUser(username, password);

        return new RedirectView("/");
    }
    @GetMapping("/")
    public String showSplashPage(){
        return "welcome.html";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login.html";
    }

    @GetMapping ("/something")
    public String showSongUsers (Principal p, Model m){

    }
}
