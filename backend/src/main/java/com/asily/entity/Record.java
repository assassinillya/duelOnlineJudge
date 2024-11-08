package com.asily.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    private Integer userId;

    private Integer problemId;

    private Integer contestId;

    private String status;

    private LocalDateTime timestamp;
}