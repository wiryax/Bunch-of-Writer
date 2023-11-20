package com.enigma.bunchofwriters.service.impl;

import com.enigma.bunchofwriters.dto.request.ArticelRequest;
import com.enigma.bunchofwriters.dto.request.ReferenceRequest;
import com.enigma.bunchofwriters.dto.request.RequestArticleMeta;
import com.enigma.bunchofwriters.dto.response.ArticleMetaResponse;
import com.enigma.bunchofwriters.dto.response.ArticleResponse;
import com.enigma.bunchofwriters.dto.response.ReferenceResponse;
import com.enigma.bunchofwriters.model.Article;
import com.enigma.bunchofwriters.model.Author;
import com.enigma.bunchofwriters.repository.ArticleRepository;
import com.enigma.bunchofwriters.service.ArticleMetaService;
import com.enigma.bunchofwriters.service.ArticleService;
import com.enigma.bunchofwriters.service.AuthorService;
import com.enigma.bunchofwriters.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMetaService articleMetaService;
    private final ReferenceService referenceService;
    private final AuthorService authorService;

    @Transactional
    @Override
    public ArticleResponse save(ArticelRequest articelRequest) {
        Author author = authorService.selectById(articelRequest.getAuthorId());
        Article article = Article.builder()
                .id(UUID.randomUUID().toString())
                .tittle(articelRequest.getTitle())
                .publisDate(LocalDateTime.now())
                .author(author)
                .status("publish")
                .build();
        articleRepository.insertArticle(
                article.getId(),
                article.getTittle(),
                article.getAuthor().getId(),
                article.getPublisDate(),
                article.getStatus(),
                null
        );
        /*        List<ReferenceRequest> references = articelRequest.getReferences().stream().map(r -> {
            return ReferenceRequest.builder()
                    .articleId(article.getId())
                    .url(r.getUrl())
                    .title(r.getTitle())
                    .build();
        }).collect(Collectors.toList());
        RequestArticleMeta requestArticleMeta = RequestArticleMeta.builder()
                .articleId(article.getId())
                .text(articelRequest.getText())
                .build();*/
        Map<String, Object> articleData = getArticleData(articelRequest, article.getId());
        articleMetaService.inserArticleMeta((RequestArticleMeta) articleData.get("articleMeta"));
        referenceService.insertBulkReference((List<ReferenceRequest>) articleData.get("references"));
        return ArticleResponse.builder()
                .title(article.getTittle())
                .build();
    }

    @Override
    public List<ArticleResponse> findAll() {
        List<Article> articles = articleRepository.selectAll();

        List<ArticleResponse> articleResponses = getArticleResponses(articles);
        return articleResponses;
    }

    private List<ArticleResponse> getArticleResponses(List<Article> articles) {
        List<ArticleResponse> articleResponses = articles.stream().map(article -> {
            return ArticleResponse.builder()
                    .title(article.getTittle())
                    .articleMetaResponse(ArticleMetaResponse.builder()
                            .text(article.getArticleMeta().getText())
                            .build())
                    .referenceResponses(article.getReferences().stream().map(reference -> {
                        return ReferenceResponse.builder()
                                .title(reference.getTitle())
                                .url(reference.getUrl())
                                .build();
                    }).collect(Collectors.toList())).build();
        }).collect(Collectors.toList());
        return articleResponses;
    }
    private Map<String, Object> getArticleData(ArticelRequest article, String articleId){
        List<ReferenceRequest> references = article.getReferences().stream().map(r -> {
            return ReferenceRequest.builder()
                    .articleId(articleId)
                    .url(r.getUrl())
                    .title(r.getTitle())
                    .build();
        }).collect(Collectors.toList());
        RequestArticleMeta requestArticleMeta = RequestArticleMeta.builder()
                .articleId(articleId)
                .text(article.getText())
                .build();
        Map<String, Object> map = new HashMap<>();
        map.put("references", references);
        map.put("articleMeta", requestArticleMeta);

        return map;
    }

    @Override
    public List<ArticleResponse> findById(String id) {
        Article article = getById(id);
        List<Article> articles = List.of(article);
        return getArticleResponses(articles);
    }

    private Article getById(String id) {
        return articleRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ArticelRequest articelRequest) {
        Author author = authorService.selectById(articelRequest.getAuthorId());
        Article article = getById(articelRequest.getId());

        articleRepository.update(LocalDateTime.now(), articelRequest.getTitle(), article.getId());

        Map<String, Object> articleData = getArticleData(articelRequest, article.getId());

        RequestArticleMeta articleMeta = (RequestArticleMeta) articleData.get("articleMeta");
        List<ReferenceRequest> references = (List<ReferenceRequest>) articleData.get("references");

        articleMetaService.updateArticleMeta(articleMeta);
        referenceService.updateReference(references);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        Article article = getById(id);
        articleMetaService.deleteById(article.getId());
        referenceService.deleteByArticleId(article.getId());
        articleRepository.deleteById(article.getId());
    }
}
