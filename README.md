# codefellowship

## Summary

This app demonstrates basic Spring Security Authentication and Authorization functionality. It utilizes Thymeleaf and postgresql.

## How To Run

./gradle run

## Link

- [App](codefellowship/src/main/java/com/shaybrow/codefellowship/CodefellowshipApplication.java)

### Classes

- CodefellowshipUser creates the users for our site and reconstructs them when they're pulled from our DB

- UserDetailsServiceImplementation provides DB functionality for finding users by username

- WebSecurityConfig manages our password hashing and login verification

- CodefellowshipUserController manages our routes for linking to various pages and DB

- CodefellowshipUserRepo extends Jpa repository to allow searches by username through the DB

- Posts creates Posts with relationship to user


