package com.stroitel.techshop.service;

import com.stroitel.techshop.domain.Token;

public interface TokenService {

    Token findByToken(String token);

    void save(Token token);

    void delete(Token token);
}
