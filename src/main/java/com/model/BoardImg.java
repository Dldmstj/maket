package com.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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

    public void updateBoardImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
