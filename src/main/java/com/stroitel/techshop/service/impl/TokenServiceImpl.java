package com.stroitel.techshop.service.impl;

import com.stroitel.techshop.dao.TokenDAO;
import com.stroitel.techshop.domain.Token;
import com.stroitel.techshop.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenDAO tokenDAO;

    @Transactional(readOnly = true)
    @Override
    public Token findByToken(String token) {
        return tokenDAO.findByToken(token);
    }

    @Transactional
    @Override
    public void save(Token token) {
        tokenDAO.save(token);
    }

    @Transactional
    @Override
    public void delete(Token token) {
        Token tokenFromBd = tokenDAO.findByToken(token.getToken());

        tokenFromBd.setValid(false);

        tokenDAO.save(tokenFromBd);
    }
}
