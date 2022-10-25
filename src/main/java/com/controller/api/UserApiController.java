package com.controller.api;

import com.dto.ResponseDto;
import com.model.Board;
import com.model.User;
import com.model.type.GenderType;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiController {


    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(User user) {
        userService.userSave(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(User user) { // key=value, x-www-form-urlencoded
        userService.userUpdate(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPw()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/charge")
    public ResponseDto<Integer> charge(int id, int payMoney) { // key=value, x-www-form-urlencoded
        userService.charge(id, payMoney);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/withdrow")
    public ResponseDto<Integer> withdrow(int id, int payMoney) { // key=value, x-www-form-urlencoded
        userService.withdrow(id, payMoney);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/purchase")
    public ResponseDto<Integer> buy(int sellerId, int buyerId, Board board, int payMoney) { // key=value, x-www-form-urlencoded
        board.setSituation(1);
        userService.sell(sellerId, payMoney);      // 게시물작성자의 아이디
        userService.buy(buyerId, payMoney);      // 접속한 사람의 아이디
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }



/*    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
        System.out.println("UserApiController : longin 호출됨");
        User principal = userService.login(user);

        if(principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }*/
}
