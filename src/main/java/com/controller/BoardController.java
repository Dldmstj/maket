package com.controller;

import com.config.auth.PrincipalDetail;
import com.model.Board;
import com.model.type.CategoryType;
import com.model.type.GenderType;
import com.model.type.SellType;
import com.model.type.StateType;
import com.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BoardController {

    @ModelAttribute("categoryTypes")
    public CategoryType[] categoryTypes() {
        return CategoryType.values();
    }
    @ModelAttribute("sellTypes")
    public SellType[] sellTypes() {
        return SellType.values();
    }
    @ModelAttribute("stateTypes")
    public StateType[] stateTypes() {
        return StateType.values();
    }
    @Autowired
    private BoardService boardService;


    @GetMapping({"", "/", "/main"})
    public String index(Model model, @PageableDefault(sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.boardChart(pageable));
        return "main";
    }

    @GetMapping({"/serch"})
    public String search(String keyword, @PageableDefault(sort="id", direction = Sort.Direction.DESC) Pageable pageable,
                         Model model) {
        Page<Board> searchList = boardService.search(keyword, pageable);
        model.addAttribute("keyword", keyword.toString());
        model.addAttribute("searchList", searchList);
        return "/search";
    }

    @GetMapping("/auth/{category}")
    public String category(Model model, @PageableDefault(sort="id", direction = Sort.Direction.DESC) Pageable pageable,
                           @PathVariable CategoryType category) {
        Page<Board> categoryMain = boardService.boardCategory(category, pageable);
        model.addAttribute("categoryMain", categoryMain);
        model.addAttribute("category", category);
        return "view";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail,
                           @PageableDefault(size = 4, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        boardService.countVisit(id);
        model.addAttribute("board", boardService.boardDetail(id));
        model.addAttribute("principal", principalDetail.getUser());
        model.addAttribute("boards", boardService.boardChart(pageable));
        return "itemview";
    }

    @GetMapping("/board/{id}/buyForm")
    public String buyForm(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principalDetail, Model model, Board board) {
        model.addAttribute("principal", principalDetail.getUser());
        model.addAttribute("board", boardService.boardDetail(id));
        return "purchase";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model,
                             @AuthenticationPrincipal PrincipalDetail principalDetail) {
            model.addAttribute("board", boardService.boardDetail(id));
            model.addAttribute("principal", principalDetail.getUser());
        return "uploadupdate";
    }

    // USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(Model model) {
        model.addAttribute("board", new Board());
        return "upload";
    }

    @GetMapping({"/header"}) public String header(Model model, CategoryType category) {
        model.addAttribute("category", category);
        return "/fragments/header";
    }


}