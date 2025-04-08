package com.example.email_sender_service.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendEmailsToAdmins {
  private   EmailSenderService emailSenderService;
    private AdminEmailsService adminEmailsService;

    public SendEmailsToAdmins(EmailSenderService emailSenderService, AdminEmailsService adminEmailsService) {
        this.emailSenderService = emailSenderService;
        this.adminEmailsService = adminEmailsService;
    }



    public void sendMailsToAdmins(String user, String role) {
        List<String> adminEmails = adminEmailsService.getAdminEmailsFromUserService();
        System.out.println("admins: " + adminEmails);
        emailSenderService.sendAdminEmail(adminEmails, user, role);
    }
}
