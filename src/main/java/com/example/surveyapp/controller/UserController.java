package com.example.surveyapp.controller;

import com.example.surveyapp.model.User;
import com.example.surveyapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        logger.info("GET /register - Showing registration form");
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        logger.info("POST /register - Registering user with email: {}", user.getEmail());
        try {
            userService.registerUser(user);
            logger.info("User registered successfully, redirecting to login");
            return "redirect:/login?success=" + URLEncoder.encode("Kayıt başarılı! Lütfen giriş yapın.", StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("Error registering user: {}", e.getMessage(), e);
            model.addAttribute("error", "Kayıt sırasında bir hata oluştu: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        logger.info("GET /login - Showing login form");
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("email") String email, @ModelAttribute("password") String password, Model model, HttpSession session) {
        logger.info("POST /login - Attempting login with email: {}", email);
        logger.debug("Received password: {}", password);
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            logger.warn("Email or password is empty: email={}, password={}", email, password);
            model.addAttribute("error", "E-posta veya şifre boş olamaz!");
            return "login";
        }
        try {
            User user = userService.loginUser(email, password);
            logger.info("User logged in successfully: {}", user.getEmail());
            session.setAttribute("loggedInUser", user.getEmail());
            logger.info("Session set, redirecting to /surveys");
            String successMessage = URLEncoder.encode("Giriş başarılı!", StandardCharsets.UTF_8); // Türkçe karakterleri kodla
            return "redirect:/surveys?success=" + successMessage;
        } catch (Exception e) {
            logger.error("Error logging in user: {}", e.getMessage(), e);
            model.addAttribute("error", "Giriş sırasında bir hata oluştu: " + e.getMessage());
            logger.debug("Returning to login page with error: {}", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        logger.info("GET /logout - Logging out user");
        session.invalidate();
        return "redirect:/login?success=" + URLEncoder.encode("Çıkış yapıldı!", StandardCharsets.UTF_8);
    }
}