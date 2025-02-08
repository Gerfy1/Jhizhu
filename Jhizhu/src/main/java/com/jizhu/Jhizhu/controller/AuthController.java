package com.jizhu.Jhizhu.controller;

import com.jizhu.Jhizhu.dto.AuthRequest;
import com.jizhu.Jhizhu.dto.AuthResponse;
import com.jizhu.Jhizhu.entity.User;
import com.jizhu.Jhizhu.repository.UserRepository;
import com.jizhu.Jhizhu.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if(user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())){
            String token = jwtUtil.generateToken(user.get().getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(401).body("Login Inválido!");
    }
}
