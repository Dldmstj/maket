package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     // PK, Auto_increment

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;        // 게시물

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;      // 유저
}
