package com.example.email_sender_service.models;

public class EmailRequest {
    private String user ;
    private String role ;

    public EmailRequest(String user, String role) {
        this.user = user;
        this.role = role;
    }

    public EmailRequest() {
    }

    public String getRole() {
        return role;
    }

    public String getUser() {
        return user;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
