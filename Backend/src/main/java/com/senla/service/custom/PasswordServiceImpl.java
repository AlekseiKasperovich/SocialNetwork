package com.senla.service.custom;

import com.senla.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordService {

    private final PasswordEncoder bCryptPasswordEncoder;

    /** @return new generated password */
    @Override
    public String generatePassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        return passwordGenerator.generatePassword(
                16,
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1));
    }

    /**
     * @param password raw password
     * @return hashed password
     */
    @Override
    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
