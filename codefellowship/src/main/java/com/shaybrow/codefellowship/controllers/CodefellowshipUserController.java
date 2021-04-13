package com.shaybrow.codefellowship.controllers;

import com.shaybrow.codefellowship.codefellowshipUsers.CodefellowshipUser;
import com.shaybrow.codefellowship.repos.CodefellowshipUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class CodefellowshipUserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CodefellowshipUserRepo codefellowshipUserRepo;

    @PostMapping("/codeuser")
    public RedirectView createUser(String username, String password,
                                   String firstName, String lastName, String dateOfBirth,
                                   String bio){
        password = passwordEncoder.encode(password);

        CodefellowshipUser user = new CodefellowshipUser(username, password, firstName, lastName,
                dateOfBirth, bio);
        codefellowshipUserRepo.save(user);
        System.out.println(user.toString());
        return new RedirectView("/");
    }
    @GetMapping("/")
    public String showSplashPage(){
        return "welcome.html";
    }
    @GetMapping("signup")
    public String showSignupPage(){
        return "signup.html";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login.html";
    }

    @GetMapping ("/something")
    public String showSongUsers (Principal p, Model m){
    return null;
    }
    @GetMapping("/codefellow")
    public String showCodefellow (Principal p, Model m){
        m.addAttribute("username", p.getName());

        return "codefellow.html";
    }
    @GetMapping("/user/{id}")
    public String userRender(Principal p, Model m,
                             @PathVariable long id){
        CodefellowshipUser user = codefellowshipUserRepo.getOne(id);
        m.addAttribute("user", user);
        return "codeuser.html";

    }
}
