package com.zhane.laptops.Services;

import com.zhane.laptops.Models.User;
import com.zhane.laptops.Utils.LoginRequest;
import com.zhane.laptops.Utils.UserJwtResponse;
import com.zhane.laptops.Utils.UserRegistrationRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    UserJwtResponse authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(UserRegistrationRequest registrationRequest);

    void delete(String username);

    List<User> getAllUser();

    User findByUsername(String username);

}
