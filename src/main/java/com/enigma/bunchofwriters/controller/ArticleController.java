package com.enigma.bunchofwriters.controller;

import com.enigma.bunchofwriters.dto.request.ArticleRequest;
import com.enigma.bunchofwriters.dto.response.ArticleResponse;
import com.enigma.bunchofwriters.model.CommonResponse;
import com.enigma.bunchofwriters.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @PostMapping
    public ResponseEntity<?> uploadArticle(@RequestBody ArticleRequest articleRequest){
        articleService.save(articleRequest);
        return ResponseEntity.ok(articleRequest);
    }

    @GetMapping
    public ResponseEntity<?> selectAll(){
        List<ArticleResponse> articleResponses = articleService.findAll();
        CommonResponse<List<ArticleResponse>> response = CommonResponse.<List<ArticleResponse>>builder()
                .data(articleResponses)
                .message("success get all article")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> selectById(@PathVariable(name = "id") String id){
        List<ArticleResponse> articleResponses = articleService.findById(id);
        CommonResponse<List<ArticleResponse>> response = CommonResponse.<List<ArticleResponse>>builder()
                .data(articleResponses)
                .message("success get all article")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id){
        articleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success Delete Article");
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ArticleRequest articleRequest){
        articleService.update(articleRequest);
        return ResponseEntity.status(HttpStatus.OK).body("success update article");
    }
}