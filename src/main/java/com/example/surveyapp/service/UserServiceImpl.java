package com.example.surveyapp.service;

import com.example.surveyapp.model.User;
import com.example.surveyapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user) throws Exception {
        logger.info("Registering user with email: {}", user.getEmail());
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            logger.warn("User with email {} already exists", user.getEmail());
            throw new Exception("Bu e-posta adresiyle bir kullanıcı zaten kayıtlı!");
        }
        userRepository.save(user);
        logger.info("User registered successfully: {}", user.getEmail());
    }

    @Override
    public User loginUser(String email, String password) throws Exception {
        logger.debug("Logging in user with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Kullanıcı bulunamadı!"));
        logger.debug("Found user: {}", user.getEmail());
        logger.debug("Stored password: {}, Provided password: {}", user.getPassword(), password);
        if (!user.getPassword().equals(password)) {
            logger.warn("Invalid password for user: {}", email);
            throw new Exception("Geçersiz şifre!");
        }
        logger.info("User authenticated successfully: {}", email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        logger.debug("Finding user by email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + email));
    }
}