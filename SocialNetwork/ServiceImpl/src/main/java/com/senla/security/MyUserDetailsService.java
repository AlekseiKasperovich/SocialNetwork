package com.senla.security;

import com.senla.impl.model.Role;
import com.senla.impl.model.User;
import com.senla.impl.model.enums.Status;
import com.senla.impl.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with email = %s is not found", email)));
        return MyUserDetails
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(mapRoleToAuthorities(user.getRole()))
                .isActive(Status.ACTIVE.equals(user.getStatus()))
                .build();
    }

    /**
     *
     * @param role user role
     * @return list of roles
     */
    private List<SimpleGrantedAuthority> mapRoleToAuthorities(Role role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        return authorities;
    }

}
