package com.controller;

import com.config.auth.PrincipalDetail;
import com.model.User;
import com.model.type.GenderType;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @ModelAttribute("genderTypes")
    public GenderType[] genderTypes() {
        return GenderType.values();
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("/auth/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("user", new User());
        return "join";
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
        if (principalDetail != null) {
            model.addAttribute("principal",principalDetail.getUser());
        }
        return "mypage";
    }

    @GetMapping("/auth/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        model.addAttribute("principal", principalDetail.getUser());
//        model.addAttribute("user",new User());
        return "updateuser";
    }

    @GetMapping("/chareForm")
    public String chargeForm(@AuthenticationPrincipal PrincipalDetail principalDetail, Model model){
        model.addAttribute("principal", principalDetail.getUser());
        return "paymoneycharge";
    }

}
