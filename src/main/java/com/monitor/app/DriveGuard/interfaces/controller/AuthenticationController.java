package com.monitor.app.DriveGuard.interfaces.controller;


import com.monitor.app.DriveGuard.application.dto.LoginRequest;
import com.monitor.app.DriveGuard.application.dto.LoginResponse;
import com.monitor.app.DriveGuard.application.service.TokenService;
import com.monitor.app.DriveGuard.domain.model.Driver;
import com.monitor.app.DriveGuard.domain.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DriverRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        Driver driver = (Driver) auth.getPrincipal();
        var token = tokenService.generateToken(driver);
        LoginResponse loginResponse = new LoginResponse(driver.getId(), token);
        return ResponseEntity.ok(loginResponse);
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Driver data){
        if(this.repository.findByEmail(data.getEmail()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        data.setPassword(encryptedPassword);
        this.repository.save(data);

        return ResponseEntity.ok().build();
    }
}

