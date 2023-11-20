package com.enigma.bunchofwriters.service.impl;

import com.enigma.bunchofwriters.dto.request.RequestAuthor;
import com.enigma.bunchofwriters.model.Author;
import com.enigma.bunchofwriters.repository.AuthorRepository;
import com.enigma.bunchofwriters.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Author insert(RequestAuthor requestAuthor) {
        Author author = Author.builder()
                .id(UUID.randomUUID().toString())
                .nickname(requestAuthor.getNickname())
                .bio(requestAuthor.getBio())
                .build();
        authorRepository.inserAuthor
                (author.getId(), author.getNickname(), author.getBio());
        return author;
    }

    @Override
    public Author selectById(String id) {
        return getAuthor(id);
    }

    private Author getAuthor(String id) {
        return authorRepository.selectById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}