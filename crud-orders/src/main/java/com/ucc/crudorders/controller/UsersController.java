package com.ucc.crudorders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.ucc.crudorders.model.User;
import com.ucc.crudorders.model.TokenInfo;
import com.ucc.crudorders.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("public")
@RequiredArgsConstructor
public class UsersController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtUtilService;

    @PostMapping("authenticate")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getuser(), user.getpassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getuser());
            final String jwt = jwtUtilService.generateToken(userDetails);
            return ResponseEntity.ok(new TokenInfo(jwt));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).build(); 
        }
    }
}
