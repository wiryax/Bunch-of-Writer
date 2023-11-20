package com.enigma.bunchofwriters.service;

import com.enigma.bunchofwriters.dto.request.ArticelRequest;
import com.enigma.bunchofwriters.dto.response.ArticleResponse;
import com.enigma.bunchofwriters.model.Article;

import java.util.List;

public interface ArticleService {
    ArticleResponse save(ArticelRequest articelRequest);
    List<ArticleResponse> findAll();
    List<ArticleResponse> findById(String id);
    void update(ArticelRequest articelRequest);
    void delete(String id);
}
