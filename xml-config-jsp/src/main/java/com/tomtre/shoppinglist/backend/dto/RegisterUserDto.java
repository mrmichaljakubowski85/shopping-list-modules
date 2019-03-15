package com.tomtre.shoppinglist.backend.dto;

import com.tomtre.shoppinglist.backend.validation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List(
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
)
public class RegisterUserDto {

    @NotNull(message = "is required")
    @Size(max = 255, message= "max 255 characters")
    private String userName;

    @Size(max = 50, message= "max 50 characters")
    @NotNull(message = "is required")
    private String password;

    @Size(max = 50, message= "max 50 characters")
    private String matchingPassword;

    @Size(max = 255, message= "max 255 characters")
    @NotNull(message = "is required")
    private String firstName;

    @NotNull(message = "is required")
    @Size(max = 255, message= "max 255 characters")
    private String lastName;

    @NotNull(message = "is required")
    @Size(max = 255, message= "max 255 characters")
    @Email
    private String email;

    public RegisterUserDto() {

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
