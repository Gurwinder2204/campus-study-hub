package com.campusstudyhub.service;

import com.campusstudyhub.dto.UserDto;
import com.campusstudyhub.entity.User;
import com.campusstudyhub.exception.ResourceNotFoundException;
import com.campusstudyhub.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service for user management operations.
 */
@Service
@Transactional
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Register a new student user.
     * 
     * @param dto the user registration data
     * @return the created user DTO
     * @throws IllegalArgumentException if email already exists or passwords don't
     *                                  match
     */
    public UserDto register(UserDto dto) {
        log.info("Registering new user: {}", dto.getEmail());

        // Validate password match
        if (!dto.isPasswordMatching()) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Check for existing email
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already registered: " + dto.getEmail());
        }

        // Create user entity
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("ROLE_STUDENT");

        user = userRepository.save(user);
        log.info("User registered successfully: {}", user.getEmail());

        // Return DTO without password
        UserDto result = new UserDto();
        result.setId(user.getId());
        result.setFullName(user.getFullName());
        result.setEmail(user.getEmail());
        result.setRole(user.getRole());

        return result;
    }

    /**
     * Create an admin user if one doesn't exist.
     * Used by DataLoader for initial setup.
     * 
     * @param email    admin email
     * @param password admin password (will be encoded)
     * @param fullName admin full name
     */
    public void createAdminIfNotPresent(String email, String password, String fullName) {
        if (userRepository.existsByEmail(email)) {
            log.info("Admin user already exists: {}", email);
            return;
        }

        User admin = new User();
        admin.setFullName(fullName);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRole("ROLE_ADMIN");

        userRepository.save(admin);
        log.info("Admin user created: {}", email);
    }

    /**
     * Find a user by email.
     * 
     * @param email the email to search for
     * @return Optional containing the user if found
     */
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Find a user by ID.
     * 
     * @param id the user ID
     * @return the user
     * @throws ResourceNotFoundException if user not found
     */
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    /**
     * Get user by email or throw exception.
     * 
     * @param email the email
     * @return the user
     * @throws ResourceNotFoundException if not found
     */
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    /**
     * Check if email exists.
     * 
     * @param email the email to check
     * @return true if exists
     */
    @Transactional(readOnly = true)
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
