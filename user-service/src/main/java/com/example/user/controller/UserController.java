package com.example.user.controller;

import com.example.user.dto.*;
import com.example.user.security.JwtTokenProvider;
import com.example.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "API для управління користувачами")
public class UserController {
    
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    
    @PostMapping("/register")
    @Operation(summary = "Реєстрація нового користувача")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequest request) {
        UserDTO userDTO = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }
    
    @PostMapping("/login")
    @Operation(summary = "Вхід користувача та отримання JWT токена")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Отримати дані користувача за ID")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    
    @GetMapping("/email/{email}")
    @Operation(summary = "Отримати дані користувача за email")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDTO);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Оновити профіль користувача")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody RegisterRequest request) {
        UserDTO userDTO = userService.updateUser(id, request);
        return ResponseEntity.ok(userDTO);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Видалити користувача")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/validate-token")
    @Operation(summary = "Перевірити валідність JWT токена")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        boolean isValid = jwtTokenProvider.validateToken(jwt);
        return ResponseEntity.ok(isValid);
    }
}
