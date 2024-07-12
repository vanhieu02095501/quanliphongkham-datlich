package com.quanlyphongkhamvadatlich.web.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class HomeController {
    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("activePage", "home");
        return "client/pages/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("activePage", "about");
        return "client/pages/about";
    }

    @GetMapping("/procedure")
    public String procedure(Model model) {
        model.addAttribute("activePage", "procedure");
        return "client/pages/procedure";
    }

    @GetMapping("/faqs")
    public String faqs(Model model) {
        model.addAttribute("activePage", "faqs");
        return "client/pages/faqs";
    }
}
