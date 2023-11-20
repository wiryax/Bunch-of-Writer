package com.enigma.bunchofwriters.service.impl;

import com.enigma.bunchofwriters.dto.request.RequestArticleMeta;
import com.enigma.bunchofwriters.dto.response.ArticleMetaResponse;
import com.enigma.bunchofwriters.model.Article;
import com.enigma.bunchofwriters.model.ArticleMeta;
import com.enigma.bunchofwriters.repository.ArticleMetaRepository;
import com.enigma.bunchofwriters.service.ArticleMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleMetaServiceImpl implements ArticleMetaService {
    private final ArticleMetaRepository articleMetaRepository;

    @Transactional
    @Override
    public void inserArticleMeta(RequestArticleMeta requestArticleMeta) {
        ArticleMeta articleMeta = ArticleMeta.builder()
                .id(UUID.randomUUID().toString())
                .article(Article.builder().id(requestArticleMeta.getArticleId()).build())
                .text(requestArticleMeta.getText())
                .build();
        articleMetaRepository.insertArticleMeta(articleMeta.getId(), articleMeta.getText(), articleMeta.getArticle().getId());
    }

    @Override
    public ArticleMetaResponse findById(String id) {
        ArticleMeta articleMeta = getById(id);
        return ArticleMetaResponse.builder()
                .text(articleMeta.getText())
                .build();
    }

    private ArticleMeta getById(String id) {
        return articleMetaRepository.selectById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        ArticleMeta articleMeta = getById(id);
        articleMetaRepository.deleteById(articleMeta.getArticle().getId());
    }

    @Override
    public void updateArticleMeta(RequestArticleMeta requestArticleMeta) {
        articleMetaRepository.update(requestArticleMeta.getText(), requestArticleMeta.getArticleId());
    }
}
