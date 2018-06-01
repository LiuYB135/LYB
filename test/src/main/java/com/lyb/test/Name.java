package com.lyb.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Name {

    @RequestMapping("/getName")
    public String name(){
        return "我是钟吴斌！";
    }
}
