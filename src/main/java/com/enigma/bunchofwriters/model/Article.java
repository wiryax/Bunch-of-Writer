package com.enigma.bunchofwriters.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "m_article")
public class Article {
    @Id
    private String id;
    @JoinColumn(name = "author_id")
    @ManyToOne
    private Author author;
    @OneToOne(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private ArticleMeta articleMeta;
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Reference> references;
    @Column(name = "publish_date")
    private LocalDateTime publisDate;
    private String tittle;
    private String status;
    private LocalDateTime editedDate;
}
