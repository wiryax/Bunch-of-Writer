package com.enigma.bunchofwriters.service;

import com.enigma.bunchofwriters.dto.request.ArticleRequest;
import com.enigma.bunchofwriters.dto.response.ArticleResponse;

import java.util.List;

public interface ArticleService {
    ArticleResponse save(ArticleRequest articleRequest);
    List<ArticleResponse> findAll();
    List<ArticleResponse> findById(String id);
    void update(ArticleRequest articleRequest);
    void delete(String id);
}
