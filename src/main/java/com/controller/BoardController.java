package com.controller;

import com.model.Board;
import com.model.type.CategoryType;
import com.model.type.GenderType;
import com.model.type.SellType;
import com.model.type.StateType;
import com.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("boards", boardService.글목록(pageable));
        return "main"; // viewResolver 작동!!
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));

        return "itemview";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        return "uploadupdate";
    }

    // USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(Model model) {
        model.addAttribute("board", new Board());
        return "upload";
    }
}