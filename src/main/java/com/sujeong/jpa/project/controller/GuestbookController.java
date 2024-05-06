package com.sujeong.jpa.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

    @GetMapping({"/test","/list"})
    public String list(){
        return "/guestbook/list";
    }
}
