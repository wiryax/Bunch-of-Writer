package com.enigma.bunchofwriters.repository;

import com.enigma.bunchofwriters.model.ArticleMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ArticleMetaRepository extends JpaRepository<ArticleMeta, String> {

    @Modifying
    @Query(
            value = "insert into m_article_meta(id,text,article_id) values(:id, :text, :article_id)",
            nativeQuery = true
    )
    void insertArticleMeta(@Param("id") String id, @Param("text") String text, @Param("article_id")String articleId);

    @Query(
            value = "select * from m_article_meta where article_id = :id",
            nativeQuery = true
    )
    Optional<ArticleMeta> selectById(@Param("id")String id);

    @Modifying
    @Query(
            value = "delete from m_article_meta where article_id = :id",
            nativeQuery = true
    )
    void deleteById(@Param("id") String id);

    @Modifying
    @Query(
            value = "update m_article_meta set text = :text where article_id = :articleId",
            nativeQuery = true
    )
    void update(@Param("text")String text, @Param("articleId")String articleId);
}
