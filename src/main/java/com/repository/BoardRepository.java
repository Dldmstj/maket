package com.repository;

import com.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer>{

    List<Board> findByCategory(String category, Pageable pageable);

    Page<Board> findByTitleContaining(String keyword, Pageable pageable);      // 제목 검색

    @Modifying
    @Query("update Board b set b.count = b.count + 1 where b.id = :id")
    int countVisit(int id);

}