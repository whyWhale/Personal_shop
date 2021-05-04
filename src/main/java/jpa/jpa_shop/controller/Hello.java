package jpa.jpa_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Hello {
    @GetMapping("")
    public String main(Model model)
    {
        model.addAttribute("data","WhyWhale");
        return "index";
    }
}
