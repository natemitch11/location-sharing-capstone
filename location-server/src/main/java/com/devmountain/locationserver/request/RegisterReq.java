package com.devmountain.locationserver.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RegisterReq {
    @NotBlank
    @NotNull
    @Size(min = 3, max = 25)
    private String firstName;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 25)
    private String lastName;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 25)
    private String username;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 25)
    private String password;

    private Set<String> role;

    public RegisterReq(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public RegisterReq() {
    }

    public RegisterReq(String firstName, String lastName, String email, String username, String password, Set<String> role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
