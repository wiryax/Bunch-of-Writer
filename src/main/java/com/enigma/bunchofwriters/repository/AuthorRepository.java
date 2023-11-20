package com.enigma.bunchofwriters.repository;

import com.enigma.bunchofwriters.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    @Modifying
    @Query(
            value = "insert into m_author(id, nickname, bio) values(:id, :nickname, :bio)",
            nativeQuery = true
    )
    void inserAuthor(@Param("id") String id,@Param("nickname") String nickname, @Param("bio")String bio);

    @Query(
            value = "select * from m_author where id = :id",
            nativeQuery = true
    )
    Optional<Author> selectById(@Param("id") String id);
}
