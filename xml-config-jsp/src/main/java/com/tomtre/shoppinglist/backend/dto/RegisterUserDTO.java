package com.tomtre.shoppinglist.backend.dto;

import com.tomtre.shoppinglist.backend.validation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@FieldMatch.List(
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
)
public class RegisterUserDTO {

    @NotNull(message = "is required")
    private String userName;

    @NotNull(message = "is required")
    private String password;

    @NotNull(message = "is required")
    private String matchingPassword;

    @NotNull(message = "is required")
    private String firstName;

    @NotNull(message = "is required")
    private String lastName;

    @NotNull(message = "is required")
    @Email
    private String email;

    public RegisterUserDTO() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
