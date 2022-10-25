package com.repository;

import com.model.Board;
import com.model.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer>{

    Page<Board> findByCategory(CategoryType category, Pageable pageable);

    Page<Board> findByTitleContaining(String keyword, Pageable pageable);      // 제목 검색

/*    Page<Board> wish(int userId, Pageable pageable);*/

    @Modifying
    @Query("update Board b set b.count = b.count + 1 where b.id = :id")
    int countVisit(int id);

}