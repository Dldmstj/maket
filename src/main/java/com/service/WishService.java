package com.service;

import com.model.Board;
import com.model.User;
import com.model.Wish;
import com.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    @Transactional
    public void wish(Wish wish, Board board, User user) throws Exception { // title, content
        wish.setUser(user);
        wish.setBoard(board);

        wishRepository.save(wish);
    }       // 찜
}
