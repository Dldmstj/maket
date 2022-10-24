package com.repository;

import com.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Integer>{

/*    Page<Board> findByCategory(String category);*/

    @Modifying
    @Query("update Board b set b.count = b.count + 1 where b.id = :id")
    int countVisit(int id);

}