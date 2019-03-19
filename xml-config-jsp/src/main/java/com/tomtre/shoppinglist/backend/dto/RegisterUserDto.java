package com.tomtre.shoppinglist.backend.dto;

import com.tomtre.shoppinglist.backend.validation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List(
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
)
public class RegisterUserDto {

    @NotEmpty(message = "is required")
    @Size(max = 255, message= "max 255 characters")
    private String username;

    @NotEmpty(message = "is required")
    @Size(min = 6, max = 50, message= "from 6 to 50 characters")
    private String password;

    @NotEmpty(message = "is required")
    @Size(min = 6, max = 50, message= "from 6 to 50 characters")
    private String matchingPassword;

    @Size(max = 255, message= "max 255 characters")
    private String firstName;

    @NotEmpty(message = "is required")
    @Size(max = 255, message= "max 255 characters")
    private String lastName;

    @NotEmpty(message = "is required")
    @Size(max = 255, message= "max 255 characters")
    @Email
    private String email;

    public RegisterUserDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
