package com.controller;

import com.config.auth.PrincipalDetail;
import com.model.Board;
import com.model.User;
import com.model.type.GenderType;
import com.service.UserService;
import com.validator.CheckNicknameValidator;
import com.validator.CheckPhoneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    @ModelAttribute("genderTypes")
    public GenderType[] genderTypes() {
        return GenderType.values();
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    private final CheckPhoneValidator checkUsernameValidator;
    private final CheckNicknameValidator checkNicknameValidator;
    private final CheckPhoneValidator checkPhoneValidator;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUsernameValidator);
        binder.addValidators(checkNicknameValidator);
        binder.addValidators(checkPhoneValidator);
    }



    @GetMapping("/auth/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("user", new User());
        return "join";
    }

    @PostMapping("/auth/joinProc")
    public String joinProc(@Valid User user, Errors errors, Model model) {
        if (errors.hasErrors()) {
            // 회원가입 실패시 입력 데이터 값을 유지
            model.addAttribute("user", user);
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            // 회원가입 페이지로 다시 리턴
            return "join";
        }
        userService.userSave(user);
        model.addAttribute("message", "정상적으로 처리되었습니다.");
        return "login";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(@RequestParam(value = "error", required = false)String error,
                            @RequestParam(value = "exception", required = false)String exception,
                            Model model) {
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "login";
    }

    @GetMapping("/auth/findIdForm")
    public String findIdForm(@RequestParam(value = "error", required = false)String error,
                            @RequestParam(value = "exception", required = false)String exception,
                            Model model) {
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "findId";
    }

    @GetMapping("/auth/findid2")
    public String findIdForm2(String name, String email, Model model) {
        model.addAttribute("user", userService.findId(name, email));        // user
        return "findid2";
    }

    @GetMapping("/myPageForm")
    public String myPageForm(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
            model.addAttribute("principal",principalDetail.getUser());
        return "mypage";
    }

    @GetMapping("/auth/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        model.addAttribute("principal", principalDetail.getUser());
//        model.addAttribute("user",new User());
        return "updateuser";
    }

    @GetMapping("/chargeForm")
    public String chargeForm(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model){
        model.addAttribute("user", principalDetail.getUser());
        return "paymoneycharge";
    }
    @GetMapping("/chargeForm2")
    public String chargeForm2(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        model.addAttribute("principal", principalDetail);
        return "paymoneycharge2";
    }

    @GetMapping("/withdrowForm")
    public String withdrowForm(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model){
        model.addAttribute("principal", principalDetail.getUser());
        return "paymoneyout";
    }

}
