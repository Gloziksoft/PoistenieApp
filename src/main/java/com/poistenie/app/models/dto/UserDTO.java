package com.poistenie.app.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for user registration data.
 */
public class UserDTO {

    @Email(message = "Zadajte platný e-mail.")
    @NotBlank(message = "E-mail je povinný.")
    private String email; // user's email address

    @NotBlank(message = "Heslo je povinné.")
    @Size(min = 3, message = "Heslo musí mať aspoň 6 znakov.")
    private String password; // user's password

    @NotBlank(message = "Potvrdenie hesla je povinné.")
    @Size(min = 3, message = "Potvrdené heslo musí mať aspoň 6 znakov.")
    private String confirmPassword; // password confirmation

    //region: getters and setters

    /**
     * Gets the user's email address.
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the password confirmation.
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    //endregion
}
