package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;


public interface UserService {

    void insertUser(User userEntity);

    List<User> getUserInfo(String userName, String pwd);


}
