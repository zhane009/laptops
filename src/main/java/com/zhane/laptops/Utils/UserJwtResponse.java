package com.zhane.laptops.Utils;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserJwtResponse {

    private String username;

    private String jwtToken;

    private String type = "Bearer";

    private Set<String> roles;

    public UserJwtResponse( String username, String jwtToken, Set<String> roles) {
        super();
        this.username = username;
        this.jwtToken = jwtToken;
        this.roles = roles;
    }

}