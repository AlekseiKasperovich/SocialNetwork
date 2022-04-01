package com.senla.service;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface EmailService {

    void sendMessage(String to, String subject, String text);
}
