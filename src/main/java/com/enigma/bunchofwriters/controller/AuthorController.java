package com.enigma.bunchofwriters.controller;

import com.enigma.bunchofwriters.dto.request.RequestAuthor;
import com.enigma.bunchofwriters.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody RequestAuthor requestAuthor){
        authorService.insert(requestAuthor);
        return ResponseEntity.ok(requestAuthor);
    }
}
