package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     // PK, Auto_increment

    @Column(nullable = false, length = 200)
    private String content;     // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;        // 댓글이 달릴 게시물

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;      // 작성자

    @CreationTimestamp
    private Timestamp createDate;       // 댓글 작성 시간
}
