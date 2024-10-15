package com.bootcamp.walletapp.controller;


import com.bootcamp.walletapp.entity.User;
import com.bootcamp.walletapp.exception.InsufficientBalanceException;
import com.bootcamp.walletapp.exception.UserAlreadyExistsException;
import com.bootcamp.walletapp.exception.UserNotFoundException;
import com.bootcamp.walletapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Localhost:8080/user/register


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Localhost:8080/
    @GetMapping("/")
    public String home(){
        return "Welcome to Wallet App";
    }

    //Localhost:8080/register?username=abc&password=123
    @PostMapping("/register")
    public ResponseEntity<?> newUser(@RequestParam String username,@RequestParam String password){
        try{
        User newuser = userService.register(username,password);
            return ResponseEntity.ok("User Registered : "+ newuser.getUsername());}
        catch (UserAlreadyExistsException e){
            return ResponseEntity.status(409).body("User already exists");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getCause());
        }
    }


}
