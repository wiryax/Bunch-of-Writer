package com.enigma.bunchofwriters.repository;

import com.enigma.bunchofwriters.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

    @Modifying
    @Query(
            value = "insert into m_article(id, author_id, edited_date, publish_date, status, tittle) values (:id, :authorId,:editedDate, :publishDate, :status, :tittle)",
            nativeQuery = true)
    void insertArticle(@Param("id") String id,@Param("tittle") String title, @Param("authorId") String authorId, @Param("publishDate")LocalDateTime publisDate, @Param("status") String status, @Param("editedDate") LocalDateTime editedDate);

    @Query(
            value = "select * from m_article",
            nativeQuery = true
    )
    List<Article> selectAll();

    @Query(
            value = "select * from m_article where id = :id",
            nativeQuery = true
    )
    Optional<Article> selectById(@Param("id") String id);

    @Modifying
    @Query(
            value = "delete from m_article where id = :id",
            nativeQuery = true
    )
    void deleteById(@Param("id") String id);

    @Modifying
    @Query(
            value = "update m_article set edited_date = :editedDate, tittle = :title where id = :id",
            nativeQuery = true
    )
    void update(@Param("editedDate")LocalDateTime editedDate, @Param("title") String title, @Param("id") String id);
}