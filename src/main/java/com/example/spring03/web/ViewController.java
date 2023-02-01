package com.example.spring03.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/view")
public class ViewController {
    
    
    @GetMapping
    public String main(){
        log.info("main()...");

        return "main";
    }
    
    @GetMapping("/login")
    public String loginPage() {
        log.info("login()");
        
        return "/view/login";
        
    }
    
    @GetMapping("/example")
    public String example() {
        log.info("example()");
        
        return "/view/example";
    }
    

    
}

