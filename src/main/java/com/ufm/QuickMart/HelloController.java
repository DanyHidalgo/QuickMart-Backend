package com.ufm.QuickMart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hola")
    public String sayHello() {
        return "Hola Mundo";
    }
}
