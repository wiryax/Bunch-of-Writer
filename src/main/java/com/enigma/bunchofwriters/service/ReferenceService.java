package com.enigma.bunchofwriters.service;

import com.enigma.bunchofwriters.dto.request.ReferenceRequest;
import com.enigma.bunchofwriters.dto.response.ReferenceResponse;

import java.util.List;

public interface ReferenceService {
    void insertBulkReference (List<ReferenceRequest> request);
    List<ReferenceResponse> selectByArticleId(String id);
    void deleteByArticleId(String id);
    void updateReference(List<ReferenceRequest> referenceRequest);
}
