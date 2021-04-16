package com.shaybrow.codefellowship.controllers;

import com.shaybrow.codefellowship.constructors.CodefellowshipUser;
import com.shaybrow.codefellowship.repos.CodefellowshipUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class CodefellowshipUserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CodefellowshipUserRepo codefellowshipUserRepo;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/codeuser")
    public RedirectView createUser(String username, String password,
                                   String firstName, String lastName, String dateOfBirth,
                                   String bio, HttpServletRequest httpServletRequest){
        String passwordEncoded = passwordEncoder.encode(password);

        CodefellowshipUser user = new CodefellowshipUser(username, passwordEncoded, firstName, lastName,
                dateOfBirth, bio);
        try {
            codefellowshipUserRepo.save(user);
        }catch (Exception e){
            return new RedirectView("/?username=duplicate");
        }
        System.out.println(user.toString());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
        token.setDetails(new WebAuthenticationDetails(httpServletRequest));
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return new RedirectView("/myprofile");
    }
    @GetMapping("/")
    public String showSplashPage(){
//        CodefellowshipUser user1 = codefellowshipUserRepo.findByUsername(p.getName());
//        m.addAttribute("prince" , user1);

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


    @GetMapping("/user")
    public String seeAllUsers (Principal p, Model m){
        List<CodefellowshipUser> users = codefellowshipUserRepo.findAll();
        CodefellowshipUser user1 = codefellowshipUserRepo.findByUsername(p.getName());
        m.addAttribute("prince" , user1);
        m.addAttribute("users", users);

        return "siteusers";
    }
    @GetMapping("/user/{id}")
    public String userRender(Principal p, Model m,
                             @PathVariable long id){
        CodefellowshipUser user = codefellowshipUserRepo.findById(id).get();
        m.addAttribute("user", user);
        CodefellowshipUser visitor = codefellowshipUserRepo.findByUsername(p.getName());
        m.addAttribute("prince", visitor);
        return "codeuser.html";

    }
    @GetMapping("/myprofile")
    public String myProfileRender (Principal p, Model m){
        CodefellowshipUser user = codefellowshipUserRepo.findByUsername(p.getName());
        m.addAttribute("prince",user);
        m.addAttribute("user", user);

        return "codeuser.html";

    }
    @PostMapping("/follow/{id}")
    public RedirectView followUser (String username, Principal p, @PathVariable long id){
        CodefellowshipUser follower = codefellowshipUserRepo.findByUsername(p.getName());
        CodefellowshipUser followed = codefellowshipUserRepo.findById(id).get();
        followed.followers.add(follower);
        codefellowshipUserRepo.save(followed);
        return new RedirectView("/user/{id}");
    }

//    @PutMapping("/user/{id}")
//    public RedirectView update (@PathVariable long id, String bio){
//        CodefellowshipUser user = codefellowshipUserRepo.findById(id).get();
//        return RedirectView "codeuser.html";
//    }
}
