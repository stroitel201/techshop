package com.stroitel.techshop.security.jwt;

import com.stroitel.techshop.domain.Role;
import com.stroitel.techshop.domain.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserAccount userAccount){
        return new JwtUser(
                userAccount.getId(),
                userAccount.getName(),
                userAccount.getEmail(),
                userAccount.getPassword(),
                userAccount.isActive(),
                mapToGrantedAuthorities(userAccount.getRoles())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> set){
        return new ArrayList<>(set).stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getTitle()))
                .collect(Collectors.toList());
    }
}
