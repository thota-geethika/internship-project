package com.m2p.backend.authentication.controller;

import com.m2p.backend.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<Boolean> userIsValid(@RequestParam(name="user") String user, @RequestParam(name="password")  String password){

        Boolean value = authenticationService.userIsValid(user,password);

        if(value){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
    }

}
