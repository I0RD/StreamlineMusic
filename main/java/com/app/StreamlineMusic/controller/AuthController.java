package com.app.StreamlineMusic.controller;

import com.app.StreamlineMusic.service.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static AuthService authService;

    public AuthController(AuthService authService) {
        AuthController.authService = authService;
    }


    public static Boolean login(String email,String password){
        return authService.login(email,password);
    }

    public static String register(String name,String userName, String email, String password){
        String response= authService.register(name,userName,email,password);
        return response;
    }
}
