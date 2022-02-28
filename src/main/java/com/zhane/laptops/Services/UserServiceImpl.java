package com.zhane.laptops.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.zhane.laptops.Models.Role;
import com.zhane.laptops.Models.User;
import com.zhane.laptops.Repositories.RoleRepository;
import com.zhane.laptops.Repositories.UserRepository;
import com.zhane.laptops.Utils.LoginRequest;
import com.zhane.laptops.Utils.UserJwtResponse;
import com.zhane.laptops.Utils.UserRegistrationRequest;
import com.zhane.laptops.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    @Qualifier("userAuthenticationManager")
    private AuthenticationManager authenticationManager;

    @Override
    public UserJwtResponse authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String jwtToken = jwtUtils.generateJwtToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Set<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        return new UserJwtResponse(userDetails.getUsername(), jwtToken, roles);
    }

    @Override
    public ResponseEntity<?> registerUser(UserRegistrationRequest registrationRequest) {
        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        if (!registrationRequest.getPassword().equals(registrationRequest.getMatchingPassword())) {
            return ResponseEntity.badRequest().body("Error: Passwords do not match!");
        }

        Set<String> strRoles = registrationRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            roles.add(roleRepository.findByName("ROLE_" + role.toUpperCase()));
        });

        User user = new User(registrationRequest.getUsername(),
                passwordEncoder.encode(registrationRequest.getPassword()), roles);
        userRepository.saveAndFlush(user);

        return ResponseEntity.ok("Registration Success.");

    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}