package com.example.demo.calculatrice.web;

import com.example.demo.calculatrice.model.LoginResponse;
import com.example.demo.calculatrice.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@Scope("session")
public class LoginController {
    private User user = new User();

    @PostMapping("/login")
    public LoginResponse postBody(@RequestBody User user) {
        if (user.getName().equals(user.getPassword())) {
            this.user = user;
            return new LoginResponse(true, "sa77it");
        } else {
            throw new IllegalArgumentException("Sa7iff");
        }
    }

    @GetMapping("/user")
    public User getUser() {
        return user;
    }
}
