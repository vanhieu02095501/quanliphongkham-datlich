package com.quanlyphongkhamvadatlich.web.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class HttpExceptionPageController {
    
    @GetMapping("/403")
    public String Forbidden() {
        return "error-page/403";
    }
}
