package com.enigma.bunchofwriters.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReferenceRequest {
    private String id;
    private String articleId;
    private String title;
    private String url;
}
