package com.example.security_service_amigos_code.auth;

import lombok.*;




public class RegisterRequest {
    private String firstName ;
    private String email ;
    private String lastName;
    private String password ;

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequest() {
    }

    public RegisterRequest(String firstName, String email, String lastName, String password) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.password = password;
    }
}
