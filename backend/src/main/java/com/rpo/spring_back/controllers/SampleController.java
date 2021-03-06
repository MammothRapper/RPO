package com.rpo.spring_back.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class SampleController {

    // методу getTitle будт передаваться клиентские GET запросы
    // с локальной частью URL сложенной из “/api/v1” и “title”.
    @GetMapping("/title")
    public String getTitle() {
        return "<title>Hello from Backend</title>";
    }
}
