package com.ecommerce.ptcommerce.service;

import com.ecommerce.ptcommerce.model.UserLogin;
import com.ecommerce.ptcommerce.model.UserRegis;
import com.ecommerce.ptcommerce.model.UserRegistration;
import com.ecommerce.ptcommerce.model.request.ChangeAvatarRequest;
import com.ecommerce.ptcommerce.repository.UserInterface;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserInterface userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    private JwtService jwtService;

    public AuthenticationService(
        UserInterface userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegistration signup(UserRegis input) {
        UserRegistration user = new UserRegistration();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public UserRegistration authenticate(UserLogin input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

    public UserRegistration changeAvatar(String token, ChangeAvatarRequest request){
        final String jwt = token.substring(7);
        final String userId = jwtService.extractUserId(jwt);

        UserRegistration user = userRepository.findById(userId).orElseThrow();

        user.setAvatar(request.getAvatar());
        return userRepository.save(user);
    }

    public Boolean isAvailableEmail(String req){

        UserRegistration user = userRepository.findByEmail(req).orElseThrow();
        if(user != null){
            return true;
        }
        return false;
    }

    
}
