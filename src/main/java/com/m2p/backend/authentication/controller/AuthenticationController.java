package com.m2p.backend.authentication.controller;

import com.m2p.backend.authentication.model.UserDetails;
import com.m2p.backend.authentication.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<Boolean> userIsValid(@RequestParam String user,@RequestParam  String password){
        return new ResponseEntity<>(authenticationService.userIsValid(user,password), HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserDetails userDetails)
    {
        authenticationService.createUser(userDetails);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/check-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean userExistsOrNot(@RequestParam() String name)
    {
        return authenticationService.checkUserAvailability(name);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/check-email")
    @ResponseStatus(HttpStatus.OK)
    public boolean emailExistsOrNot(@RequestParam String email)
    {
        return authenticationService.checkEmailAvailability(email);
    }
}
