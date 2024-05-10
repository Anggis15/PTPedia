package com.ecommerce.ptcommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ptcommerce.constant.MessageResponse;
import com.ecommerce.ptcommerce.entity.response.ErrorResponse;
import com.ecommerce.ptcommerce.entity.response.SuccesResponse;
import com.ecommerce.ptcommerce.model.UserLogin;
import com.ecommerce.ptcommerce.model.UserRegis;
import com.ecommerce.ptcommerce.model.UserRegistration;
import com.ecommerce.ptcommerce.model.request.ChangeAvatarRequest;
import com.ecommerce.ptcommerce.model.response.LoginResponse;
import com.ecommerce.ptcommerce.service.AuthenticationService;
import com.ecommerce.ptcommerce.service.JwtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/v1/signup")
    public ResponseEntity<?> register(@RequestBody UserRegis registerUserDto) {
        UserRegistration registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(new SuccesResponse<>(MessageResponse.SUCCESS_CREATE.toString(), registeredUser));
    }

    @PostMapping("/v1/login")
    public ResponseEntity<?> authenticate(@RequestBody UserLogin loginUserDto) {
        UserRegistration authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(new SuccesResponse<>(MessageResponse.SUCCESS_GET_DATA.toString(), loginResponse));
    }

    @PostMapping("/v1/changeavatar")
    public ResponseEntity<?> postMethodName(@RequestBody ChangeAvatarRequest reqUser, @RequestHeader("Authorization") String token) {
        
        UserRegistration user = authenticationService.changeAvatar(token, reqUser);

        //TODO: process POST request
        
        
        return ResponseEntity.ok(new SuccesResponse<>(MessageResponse.SUCCESS_UPDATE_DATA.toString(), user));
    }

    @GetMapping("/v1/user/isavailable/{email}")
    public ResponseEntity<?> getMethodName(@PathVariable String email) {
        if (authenticationService.isAvailableEmail(email)){
            return ResponseEntity.badRequest().body(new ErrorResponse("Ga Bisa", email));
        }
        return ResponseEntity.ok(new SuccesResponse<>("OK", "Email Bisa Di Gunakan"));
    }
    

}