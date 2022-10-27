package com.repository;

import com.model.Board;
import com.model.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WishRepository extends JpaRepository<Wish, Integer> {

   /* @Query(value = "select * FROM wish w where w.userId = ?1",nativeQuery = true)
    int findByUserId(int id);*/
}
