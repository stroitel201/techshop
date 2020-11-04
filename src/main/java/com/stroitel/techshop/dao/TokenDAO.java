package com.stroitel.techshop.dao;


import com.stroitel.techshop.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenDAO extends CrudRepository<Token, Long> {

    Token findByToken(String token);
}
