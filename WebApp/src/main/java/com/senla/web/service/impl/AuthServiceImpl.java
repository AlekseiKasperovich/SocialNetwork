package com.senla.web.service.impl;

import com.senla.web.dto.token.TokenDto;
import com.senla.web.dto.user.DtoCreateUser;
import com.senla.web.dto.user.ForgotPasswordDto;
import com.senla.web.dto.user.LoginUserDto;
import com.senla.web.exception.MyAccessDeniedException;
import com.senla.web.exception.MyServerErrorException;
import com.senla.web.exception.UserAlreadyExistException;
import com.senla.web.exception.UserNotFoundException;
import com.senla.web.feign.AuthClient;
import com.senla.web.security.CurrentUserDetails;
import com.senla.web.security.SecurityUtil;
import com.senla.web.service.AuthService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthClient authClient;

    @Override
    public void registerNewUserAccount(DtoCreateUser createUserDto) {
        try {
            authClient.createUser(createUserDto);
        } catch (FeignException.Conflict ex) {
            throw new UserAlreadyExistException(
                    String.format(
                            "Registration failed! There is an account with that email address = %s",
                            createUserDto.getEmail()));
        }
    }

    @Override
    public void login(LoginUserDto loginUserDto) {
        try {
            TokenDto tokenDto = authClient.login(loginUserDto).getBody();
            if (tokenDto != null) {
                String token = tokenDto.getToken();
                CurrentUserDetails currentUserDetails =
                        CurrentUserDetails.builder()
                                .email(loginUserDto.getEmail())
                                .token(token)
                                .authorities(SecurityUtil.AUTHORITIES)
                                .build();
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                currentUserDetails, null, currentUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (FeignException.Unauthorized ex) {
            throw new MyAccessDeniedException("Your account has been deleted or blocked");
        } catch (FeignException.Forbidden ex) {
            throw new MyAccessDeniedException("Incorrect email or password");
        }
    }

    @Override
    public void sendPassword(ForgotPasswordDto forgotPasswordDto) {
        try {
            authClient.sendPassword(forgotPasswordDto);
        } catch (FeignException.Conflict ex) {
            throw new UserNotFoundException(
                    String.format(
                            "User with email = %s is not found!", forgotPasswordDto.getEmail()));
        } catch (FeignException.InternalServerError ex) {
            throw new MyServerErrorException(
                    "Something went wrong! Your new password was not sent, please try again"
                            + " later!");
        }
    }
}
