package jpa.jpa_shop.web.controller;

import jpa.jpa_shop.service.CategoryService;
import jpa.jpa_shop.web.LogExecutionTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Slf4j
@Controller
public class Home {

    private final CategoryService categoryService;

    @LogExecutionTime
    @GetMapping("")
    public String main(Model model)
    {
        model.addAttribute("categories",categoryService.createCategoryList());
        return "index";
    }
}
