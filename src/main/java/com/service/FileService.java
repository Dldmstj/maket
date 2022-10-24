package com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class FileService {

    public String uploadFile(String uploadPath, String oriImgName, byte[] fileData) throws Exception {
        UUID uuid = UUID.randomUUID();      // img 이름 중복 방지
        String extention = oriImgName.substring(oriImgName.lastIndexOf("."));
        String saveImgName = uuid.toString() + extention;
        String fileUploadFullUrl = uploadPath + "/" + saveImgName;

        // 파일 출력 스트림
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();

        return saveImgName;
    }

    public void deleteFile(String filePath) throws Exception {
        File deleteFile = new File(filePath);
    }
}
