package com.asily.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;

@Data
@Entity
@Table(name = "duel")
public class Duel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer duelId;

    private Integer user1Id;

    private Integer user2Id;

    private Integer ratingChangeUser1;

    private Integer ratingChangeUser2;

    private Duration timeLimit;

    private String difficultyLevel;
}
