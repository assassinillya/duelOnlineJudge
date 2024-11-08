package com.asily.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contest_problem_set")
public class ContestProblemSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer problemId;

    private Integer contestId;
}
