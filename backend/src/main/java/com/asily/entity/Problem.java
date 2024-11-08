package com.asily.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "problem")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer problemId;

    private String title;

    private String difficulty;

    @Column(columnDefinition = "TEXT")
    private String description;
}