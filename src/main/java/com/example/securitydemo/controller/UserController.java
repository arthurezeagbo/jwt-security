package com.example.securitydemo.controller;

import com.example.securitydemo.domain.AuthenticationRequest;
import com.example.securitydemo.domain.AuthenticationResponse;
import com.example.securitydemo.domain.User;
import com.example.securitydemo.repository.UserRepository;
import com.example.securitydemo.security.JwtUtil;
import com.example.securitydemo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtTokenUtil;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/getAll")
    public List<User> getUsersList(){
        return userRepository.findAll();
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello user";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            ));
        }catch (BadCredentialsException exception){
            throw new Exception("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
