package com.app.StreamlineMusic.service;

public interface AuthService {
    Boolean login (String email, String password);
    String register (String name,String userName, String email,String password);
}
