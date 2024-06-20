package com.example.writemymail.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
public class Prompt {
    @Id
    private String type;
    @Column(columnDefinition = "TEXT")
    private String text;
}
