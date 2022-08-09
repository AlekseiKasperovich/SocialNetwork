package com.senla.service;

/**
 * @author Aliaksei Kaspiarovich
 */
public interface PasswordService {

    String generatePassword();

    String encode(String password);
}
