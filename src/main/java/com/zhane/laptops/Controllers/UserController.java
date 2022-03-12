package com.zhane.laptops.Controllers;

import com.zhane.laptops.Models.User;
import com.zhane.laptops.Repositories.UserRepository;
import com.zhane.laptops.Services.UserServiceImpl;
import com.zhane.laptops.Utils.LoginRequest;
import com.zhane.laptops.Utils.UserRegistrationRequest;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Secured("USER")
    public List<User> getAll(){
        return userService.getAllUser();
    }

    @GetMapping("username")
    @PreAuthorize("hasRole('ROLE_USER')")
    public User getOne(@PathVariable String username){
        return userService.findByUsername(username);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.authenticateUser(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest registrationRequest){
        return ResponseEntity.ok(userService.registerUser(registrationRequest));
    }

    @RequestMapping(path = "{username}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> delete(@PathVariable String username) {
        userService.delete(username);
        return ResponseEntity.ok("Successfully deleted");
    }

    @RequestMapping(path = "{username}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable String username, @RequestBody UserRegistrationRequest user,
                                    HttpServletRequest request) {
        return userService.update(username, user, request);
    }


}
