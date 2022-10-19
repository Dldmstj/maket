package com.repository;

import java.util.Optional;

import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
public interface UserRepository extends JpaRepository<User, Integer>{
    // SELECT * FROM user WHERE username = 1?;
    Optional<User> findByUsername(String username);
}



// JPA naming 쿼리
//SELECT * FROM user WHERE username = ? AND pw = ?;
// User findByUserNameAndPw(String userName, String pw);

//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND pw = ?2", nativeQuery = true)
//    User login(String userName, String pw);