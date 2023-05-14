package com.app.StreamlineMusic.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("admin: "+passwordEncoder.encode("admin"));
        System.out.println("user: "+passwordEncoder.encode("user"));
        System.out.println("lord: "+passwordEncoder.encode("lord"));

        System.out.println("admin: "+ new BCryptPasswordEncoder().matches("lord","$2a$10$sKEdCbUlHvKSDJSNNJoA/.TscOqko2jhT4elnpKxPQsY7RF1ZWVwa"));
    }
}
