package com.asily.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contest_user_set")
public class ContestUserSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer contestId;
}
