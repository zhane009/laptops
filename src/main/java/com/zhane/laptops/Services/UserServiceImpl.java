package com.zhane.laptops.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.zhane.laptops.Models.Manufacturer;
import com.zhane.laptops.Models.Role;
import com.zhane.laptops.Models.User;
import com.zhane.laptops.Repositories.ManufacturerRepository;
import com.zhane.laptops.Repositories.RoleRepository;
import com.zhane.laptops.Repositories.UserRepository;
import com.zhane.laptops.Utils.LoginRequest;
import com.zhane.laptops.Utils.UserJwtResponse;
import com.zhane.laptops.Utils.UserRegistrationRequest;
import com.zhane.laptops.jwt.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    ManufacturerRepository manufacturerRepository;

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

        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
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

        Manufacturer manufacturer = manufacturerRepository.findByCompany(registrationRequest.getManufacturer());


        Set<String> strRoles = registrationRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            roles.add(roleRepository.findByName("ROLE_" + role.toUpperCase()));
        });

        User user = new User(registrationRequest.getUsername(),manufacturer,
                passwordEncoder.encode(registrationRequest.getPassword()), roles);
        userRepository.saveAndFlush(user);

        return ResponseEntity.ok("Registration Success.");

    }


    @Override
    public ResponseEntity<?> update(String username, UserRegistrationRequest userInfo, HttpServletRequest request) {
        String jwtToken = jwtUtils.parseJwt(request);

        String tokenUsername = jwtUtils.getUsernameFromJwtToken(jwtToken);
        UserDetails tokenUserDetails = userDetailsService.loadUserByUsername(tokenUsername);

        Set<String> tokenRoles = tokenUserDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        boolean hasAccessToUpdate = false;
        boolean isAdmin = false;

        if (tokenRoles.contains("ROLE_ADMIN")) {
            hasAccessToUpdate = true;
            isAdmin = true;
        } else if (username.equals(tokenUsername)) {
            hasAccessToUpdate = true;
        }

        Set<Role> userRoles = new HashSet<>();

        userInfo.getRoles().forEach(role -> {
            userRoles.add(roleRepository.findByName("ROLE_" + role));

        });

        User user = new User(userInfo.getUsername(), manufacturerRepository.findByCompany(userInfo.getManufacturer()), passwordEncoder.encode(userInfo.getPassword()),
                userRoles);

        if (hasAccessToUpdate) {
            User existingUser = userRepository.findByUsername(username);
            BeanUtils.copyProperties(user, existingUser, "id", "password", (isAdmin ? "" : "roles"));
            return ResponseEntity.ok(userRepository.saveAndFlush(existingUser));
        }

        return ResponseEntity.badRequest().body("Error: not allowed to update");
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