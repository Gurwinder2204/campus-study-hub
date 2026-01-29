package com.campusstudyhub.controller;

import com.campusstudyhub.dto.UserDto;
import com.campusstudyhub.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for authentication operations (login, registration).
 */
@Controller
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Display login page.
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Display registration page.
     */
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    /**
     * Process registration form.
     */
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userDto") UserDto userDto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {
        log.info("Processing registration for: {}", userDto.getEmail());

        // Check for validation errors
        if (result.hasErrors()) {
            return "register";
        }

        // Check password match
        if (!userDto.isPasswordMatching()) {
            result.rejectValue("confirmPassword", "error.userDto", "Passwords do not match");
            return "register";
        }

        try {
            userService.register(userDto);
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            log.warn("Registration failed: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
