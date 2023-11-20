package com.enigma.bunchofwriters.service;

import com.enigma.bunchofwriters.dto.request.RequestAuthor;
import com.enigma.bunchofwriters.model.Author;

public interface AuthorService {
    Author insert(RequestAuthor requestAuthor);
    Author selectById(String id);
}
