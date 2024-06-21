package com.example.writemymail.repository;

import com.example.writemymail.domain.entity.Email;
import com.example.writemymail.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {
    boolean existsByName(String name);

    Optional<Email> findByName(String Name);

    List<Email> findByUser(User user);
}
