package com.shaybrow.codefellowship.repos;

import com.shaybrow.codefellowship.constructors.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository <Post, Long>  {

}
