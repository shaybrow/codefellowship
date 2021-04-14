package com.shaybrow.codefellowship.repos;

import com.shaybrow.codefellowship.constructors.CodefellowshipUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodefellowshipUserRepo extends JpaRepository<CodefellowshipUser, Long> {
    public CodefellowshipUser findByUsername (String username);
}
