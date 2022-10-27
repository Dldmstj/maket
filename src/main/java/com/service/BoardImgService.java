package com.service;

import com.model.Board;
import com.model.BoardImg;
import com.repository.BoardImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BoardImgService {

    private String boardImgLocation = "D:/upPic";
    private final BoardImgRepository boardImgRepository;
    private final FileService fileService;

    @Transactional
    public  void saveImg(BoardImg boardImg, MultipartFile boardImgFile) throws Exception {
        String oriImgName = boardImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(boardImgLocation, oriImgName, boardImgFile.getBytes());
            imgUrl = "/images/" + imgName;
        }

        boardImg.updateBoardImg(oriImgName, imgName, imgUrl);
        boardImgRepository.save(boardImg);
    }

    @Transactional
    public  void updateImg(Long boardImgId, MultipartFile boardImgFile) throws Exception {
        if (!boardImgFile.isEmpty()) {
            BoardImg savedBoardImg = boardImgRepository.findById(boardImgId).orElseThrow(EntityNotFoundException::new);

            if (!StringUtils.isEmpty(savedBoardImg.getImgName())) {
                fileService.deleteFile(boardImgLocation + "/" + savedBoardImg.getImgName());
            }

            String oriImgName = boardImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(boardImgLocation, oriImgName, boardImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedBoardImg.updateBoardImg(oriImgName, imgName, imgUrl);
        }
    }
}
