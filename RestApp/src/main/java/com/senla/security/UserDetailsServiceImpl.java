package com.senla.security;

import com.senla.client.UserDetailsClient;
import com.senla.dto.constants.Status;
import com.senla.dto.user.RoleDto;
import com.senla.dto.user.UserDetailsDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** @author Aliaksei Kaspiarovich */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsClient userDetailsClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailsDto user = userDetailsClient.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("User with email = %s is not found", email));
        }
        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(mapRoleToAuthorities(user.getRole()))
                .isActive(Status.ACTIVE.equals(user.getStatus()))
                .build();
    }

    /**
     * @param role user role
     * @return list of roles
     */
    private List<SimpleGrantedAuthority> mapRoleToAuthorities(RoleDto role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        return authorities;
    }
}
