package com.controller.api;

import com.config.auth.PrincipalDetail;
import com.dto.ResponseDto;
import com.model.Board;
import com.model.Wish;
import com.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class WishApiController {

    @Autowired
    private WishService wishService;

    @PostMapping("/wish")
    public ResponseDto<Integer> wish(Wish wish, Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        try {
            wishService.wish(wish,board,principalDetail.getUser());
        } catch (Exception e) {
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
