package com.senla.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Aliaksei Kaspiarovich */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoCreateUser {

    private String email;
    private String password;
    private String matchingPassword;
}
