package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

    @RequestMapping(value="/ymarket")
    @Controller
public class PageController {
        @GetMapping(value = "/")
        public String main() {
            return "main";
        }

        @GetMapping(value = "/login")
        public String login() {
            return "login";
        }

        @GetMapping(value = "/join")
        public String join() {
            return "join";
        }

        @GetMapping(value = "/upload")
        public String upload() {
            return "upload";
        }

        @GetMapping(value = "/mypage")
        public String mypage() {
            return "mypage";
        }

        @GetMapping(value = "/updateuser")
        public String updateuser() {
            return "updateuser";
        }

        @GetMapping(value = "/paymoneycharge")
        public String paymoneycharge() {
            return "paymoneycharge";
        }

        @GetMapping(value = "/charge_bank")
        public String charge_bank() {
            return "charge_bank";
        }

        @GetMapping(value = "/view")
        public String view() {
            return "view";
        }

        @GetMapping(value = "/itemview")
        public String itemview() {
            return "itemview";
        }

        @GetMapping(value = "/charge_bank2")
        public String charge_bank2() {
            return "charge_bank2";
        }

        @GetMapping(value = "/charge_kakaopay")
        public String charge_kakaopay() {
            return "charge_kakaopay";
        }

        @GetMapping(value = "/charge_card")
        public String charge_card() {
            return "charge_card";
        }

        @GetMapping(value = "/charge_card2")
        public String charge_card2() {
            return "charge_card2";
        }

        @GetMapping(value = "/charge_phone")
        public String charge_phone() {
            return "charge_phone";
        }

        @GetMapping(value = "/charge_phone2")
        public String charge_phone2() {
            return "charge_phone2";
        }

        @GetMapping(value = "/notice")
        public String notice() {
            return "notice";
        }

        @GetMapping(value = "/writenotice")
        public String writenotice() {
            return "writenotice";
        }

        @GetMapping(value = "/noticeview")
        public String noticeview() {
            return "noticeview";
        }

        @GetMapping(value = "/qnaboard")
        public String qnaboard() {
            return "qnaboard";
        }

        @GetMapping(value = "/writeqna")
        public String writeqna() {
            return "writeqna";
        }


    }
