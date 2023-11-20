package com.enigma.bunchofwriters.service;

import com.enigma.bunchofwriters.dto.request.RequestArticleMeta;
import com.enigma.bunchofwriters.dto.response.ArticleMetaResponse;

public interface ArticleMetaService {
    void inserArticleMeta(RequestArticleMeta requestArticleMeta);
    ArticleMetaResponse findById(String id);
    void deleteById(String id);
    void updateArticleMeta(RequestArticleMeta requestArticleMeta);
}
