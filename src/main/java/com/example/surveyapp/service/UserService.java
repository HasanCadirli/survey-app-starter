package com.example.surveyapp.service;

import com.example.surveyapp.model.User;

public interface UserService {
    void registerUser(User user) throws Exception;
    User loginUser(String email, String password) throws Exception;
    User findUserByEmail(String email); // Yeni metot
}