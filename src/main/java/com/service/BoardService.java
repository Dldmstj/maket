package com.service;

import com.dto.ReplySaveRequestDto;
import com.model.Board;
import com.model.BoardImg;
import com.model.User;
import com.repository.BoardRepository;
import com.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardImgService boardImgService;
    private final ReplyRepository replyRepository;

    @Transactional
    public void upload(Board board, User user, List<MultipartFile> boardImgFileList) throws Exception { // title, content
        board.setCount(0);
        board.setUser(user);

        for (int i = 0, max = boardImgFileList.size(); i < max; i++) {
            BoardImg boardImg = BoardImg.builder()
                    .board(board)
                    .mainImg(i == 0 ? "Y" : "N")
                    .build();

            boardImgService.saveImg(boardImg, boardImgFileList.get(i));
        }
        boardRepository.save(board);
    }       // 게시물 업로드

    @Transactional(readOnly = true)
    public Page<Board> boardChart(Pageable pageable){
        return boardRepository.findAll(pageable);
    }       // 게시물 페이징, 목록 정렬

    @Transactional(readOnly = true)
    public Board boardDetail(int id) {
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });
    }       // 게시물 상세보기

   /* @Transactional(readOnly = true)
    public Page<Board> boardCategory(String category);*/
    // 게시물 카테고리별 상세보기

    @Transactional
    public void boardDelete(int id) {
        System.out.println("글삭제하기 : "+id);
        boardRepository.deleteById(id);
    }       // 게시물 삭제

    @Transactional
    public void update(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                }); // 영속화 완료
        board.setSell(requestBoard.getSell());
        board.setCategory(requestBoard.getCategory());
        board.setTitle(requestBoard.getTitle());
        board.setPrice(requestBoard.getPrice());
        board.setState(requestBoard.getState());
        board.setContent(requestBoard.getContent());
        board.setBoardImg(requestBoard.getBoardImg());
    }           // 게시물 수정

    @Transactional
    public int countVisit(int id) {
        return boardRepository.countVisit(id);
    }
    // 조회수 증가

    @Transactional
    public void replySave(ReplySaveRequestDto replySaveRequestDto) {
        int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
        System.out.println("BoardService : "+result);
    }

    @Transactional
    public void replyDelete(int replyId) {
        replyRepository.deleteById(replyId);
    }
}

