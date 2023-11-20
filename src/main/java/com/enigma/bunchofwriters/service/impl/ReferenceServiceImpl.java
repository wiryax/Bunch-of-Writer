package com.enigma.bunchofwriters.service.impl;

import com.enigma.bunchofwriters.dto.request.ReferenceRequest;
import com.enigma.bunchofwriters.dto.response.ReferenceResponse;
import com.enigma.bunchofwriters.repository.ReferenceRepository;
import com.enigma.bunchofwriters.service.ReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReferenceServiceImpl implements ReferenceService {
    private final ReferenceRepository referenceRepository;

    @Transactional
    @Override
    public void insertBulkReference(List<ReferenceRequest> request) {
        for (ReferenceRequest referenceRequest : request) {
            referenceRepository.InsertReference(UUID.randomUUID().toString(), referenceRequest.getArticleId(),referenceRequest.getTitle(),referenceRequest.getUrl());
        }
    }

    @Transactional(readOnly = true)
    public List<ReferenceResponse> selectByArticleId(String id){
        List<ReferenceResponse> collect = referenceRepository.selectByArticleId(id).stream().map(reference -> {
            return ReferenceResponse.builder()
                    .url(reference.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND)).getUrl())
                    .title(reference.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND)).getTitle())
                    .build();
        }).collect(Collectors.toList());
        return collect;
    }

    @Transactional
    @Override
    public void deleteByArticleId(String id) {
        selectByArticleId(id);
        referenceRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateReference(List<ReferenceRequest> referenceRequest) {
        for (ReferenceRequest request : referenceRequest) {
            referenceRepository.update(request.getUrl(), request.getTitle(), request.getArticleId());
        }
    }
}
