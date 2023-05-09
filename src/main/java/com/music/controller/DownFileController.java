package com.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DownFileController {

    @GetMapping("/dofile")
    public Object dofile(String name){



        return null;
    }
}
