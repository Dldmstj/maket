package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.type.CategoryType;
import com.model.type.SellType;
import com.model.type.StateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     // PK, Auto_increment

    @ColumnDefault("0")
    private int situation;       // 0 판매중 / 1 판매완료

    @Column(nullable = false, length = 100)
    private String title;       // 제목

    @Column(nullable = false, length = 50)
    private int price;       // 가격

    @Lob
    private String content;     // 내용

    @ColumnDefault("0")
    private int count;      // 조회수

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    private User user;      // user (작성자)

    @Enumerated(EnumType.STRING)
    private CategoryType category;      // 카테고리

    @Enumerated(EnumType.STRING)
    private StateType state;        // 제품 상태

    @Enumerated(EnumType.STRING)
    private SellType sell;      // 거래 방식

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BoardImg> boardImg;      // 게시물 사진
    
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> reply;      // 댓글

    @CreationTimestamp
    private Timestamp createDate;       // 작성일
}
