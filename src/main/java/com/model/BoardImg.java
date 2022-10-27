package com.model;


import lombok.*;

import javax.persistence.*;

@Data @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table
@Builder
@Entity
public class BoardImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;     // PK, Auto_increment

    private String imgName;     // 이미지 파일 이름

    private String oriImgName;     // 저장될 이미지 원본 이름

    private String imgUrl;      // 파일 경로

    private String mainImg;     // board 대표 이미지

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardId")
    private Board board;

    @Builder
    public BoardImg(String imgName, String oriImgName, String imgUrl, String mainImg, Board board) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.mainImg = mainImg;
        this.board = board;
    }

    public void updateBoardImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
