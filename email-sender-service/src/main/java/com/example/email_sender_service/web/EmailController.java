package com.example.email_sender_service.web;

import com.example.email_sender_service.models.EmailRequest;

import com.example.email_sender_service.service.SendEmailsToAdmins;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email/")
public class EmailController {
    private SendEmailsToAdmins sendEmailsToAdmins;
    public EmailController(SendEmailsToAdmins sendEmailsToAdmins) {
        this.sendEmailsToAdmins = sendEmailsToAdmins;
    }
    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest request) {
String user = request.getUser();
        System.out.println("user"+ user);
String role = request.getRole();
sendEmailsToAdmins.sendMailsToAdmins(user,role);
    }
}
