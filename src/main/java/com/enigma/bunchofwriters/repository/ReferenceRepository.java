package com.enigma.bunchofwriters.repository;

import com.enigma.bunchofwriters.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, String> {

    @Modifying
    @Query(
            value = "insert into m_reference(id,article_id,title,url) values(:id,:articleId,:title,:url)",
            nativeQuery = true
    )
    void InsertReference(@Param("id") String id, @Param("articleId") String articleId, @Param("title") String title, @Param("url") String url);

    @Query(
            value = "select * from m_reference where article_id = :id",
            nativeQuery = true
    )
    List<Optional<Reference>> selectByArticleId(@Param("id") String id);

    @Modifying
    @Query(
            value = "delete from m_reference where article_id = :id",
            nativeQuery = true
    )
    void deleteById(@Param("id") String id);

    @Modifying
    @Query(
            value = "update m_reference set url = :url, title = :title where article_id = :id",
            nativeQuery = true
    )
    void update(@Param("url") String url, @Param("title") String title, @Param("id")String articleId);
}
