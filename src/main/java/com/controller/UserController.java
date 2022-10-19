package com.controller;

import com.model.User;
import com.model.type.GenderType;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String loginForm() {
        return "login";
    }

    @GetMapping("/auth/myPageForm")
    public String myPageForm() {
        return "mypage";
    }

    @GetMapping("/auth/updateForm")
    public String updateForm() {
        return "updateuser";
    }

}
