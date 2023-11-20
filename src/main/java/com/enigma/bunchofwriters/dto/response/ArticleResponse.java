package com.enigma.bunchofwriters.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResponse {
    private String title;
    private ArticleMetaResponse articleMetaResponse;
    private List<ReferenceResponse> referenceResponses;
}
