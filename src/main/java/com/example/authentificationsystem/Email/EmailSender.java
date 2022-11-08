package com.example.authentificationsystem.Email;

import org.springframework.scheduling.annotation.Async;

public interface EmailSender {
    public void send(String to , String Email) ;
}
