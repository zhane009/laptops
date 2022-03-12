package com.zhane.laptops.Utils;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRegistrationRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String matchingPassword;

    @NotNull
    private String manufacturer;

    @NotNull
    private Set<String> roles;

}