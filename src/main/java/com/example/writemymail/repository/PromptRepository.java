package com.example.writemymail.repository;

import com.example.writemymail.domain.entity.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromptRepository extends JpaRepository<Prompt, String> {
    @Query("SELECT p.text FROM Prompt p WHERE p.type = :type")
    String findTextForPromptByType(String type);
}
