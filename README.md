# codefellowship

## Summary

This app demonstrates basic Spring Security Authentication and Authorization functionality. It utilizes Thymeleaf and postgresql. Users have the ability to create posts that are associated with themand other users are able to visit their page to sse their posts. Users can also follow other users. User profile will display which users are following them.

## How To Run

./gradle bootrun

## Link

- [App](codefellowship/src/main/java/com/shaybrow/codefellowship/CodefellowshipApplication.java)

### Classes

- CodefellowshipUser creates the users for our site and reconstructs them when they're pulled from our DB

- UserDetailsServiceImplementation provides DB functionality for finding users by username

- WebSecurityConfig manages our password hashing and login verification

- CodefellowshipUserController manages our routes for linking to various pages and DB

- CodefellowshipUserRepo extends Jpa repository to allow searches by username through the DB

- Posts creates Posts with relationship to user

- Postcontroller handles routes related to posts

- Post repo handles the DB for posts


