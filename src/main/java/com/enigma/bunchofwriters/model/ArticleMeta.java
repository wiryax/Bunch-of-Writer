package com.enigma.bunchofwriters.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_article_meta")
public class ArticleMeta {
    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "article_id")
    private Article article;
    @Column(columnDefinition = "TEXT")
    private String text;
}
