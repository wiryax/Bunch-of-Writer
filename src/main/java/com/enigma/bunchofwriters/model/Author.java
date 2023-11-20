package com.enigma.bunchofwriters.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_author")
public class Author {
    @Id
    private String id;
    @Column(unique = true)
    private String nickname;
    private String bio;
    @OneToMany(mappedBy = "author")
    private List<Article> articles;
}
