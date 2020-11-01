package com.stroitel.techshop.security;

import com.stroitel.techshop.domain.UserAccount;
import com.stroitel.techshop.security.jwt.JwtUser;
import com.stroitel.techshop.security.jwt.JwtUserFactory;
import com.stroitel.techshop.service.UserAccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserAccountService userAccountService;

    public JwtUserDetailsService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountService.findByUsername(username);

        if(userAccount == null){
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(userAccount);
        return jwtUser;
    }
}
