package com.alassaneniang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @GetMapping( "/hello" )
    public String getTeams () {
        return "Hello, World!";
    }

}
