package com.repository;

import java.util.Optional;

import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
public interface UserRepository extends JpaRepository<User, Integer>{
    // SELECT * FROM user WHERE username = 1?;
    Optional<User> findByUsername(String username);

    Optional<User> findByNameAndEmail(String username, String email);

    @Modifying
    @Query("update User u set u.payMoney = u.payMoney + :payMoney where u.id = :id")
    int findById(int id, int payMoney);
}