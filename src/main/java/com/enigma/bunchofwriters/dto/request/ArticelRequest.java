package com.enigma.bunchofwriters.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticelRequest {
    private String id;
    private String authorId;
    private String title;
    private String text;
    private List<ReferenceRequest> references;
}
