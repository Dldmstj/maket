package com.controller.api;

import com.config.auth.PrincipalDetail;
import com.dto.ReplySaveRequestDto;
import com.dto.ResponseDto;
import com.model.Board;
import com.model.BoardImg;
import com.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(Board board, @AuthenticationPrincipal PrincipalDetail principal,
                                     @RequestParam("boardImgFile")List<MultipartFile> boardImgList) {
        try {
            boardService.upload(board, principal.getUser(),boardImgList);
        } catch (Exception e) {
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping(value = "/api/board/{id}", produces = "text/plane; charset=utf-8")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.boardDelete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping(value = "/api/board/{id}", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
        logger.info("board update :: " + board.toString());
        boardService.update(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(ReplySaveRequestDto replySaveRequestDto) {
        boardService.replySave(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping(value = "/api/board/{boardId}/reply/{replyId}", produces = "text/plane; charset=utf-8")
    public ResponseDto<Integer> replyDelete(@PathVariable int boardId, @PathVariable int replyId) {
        boardService.replyDelete(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}