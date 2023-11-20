package com.enigma.bunchofwriters.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_reference")
public class Reference {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    private String title;
    private String url;
}
