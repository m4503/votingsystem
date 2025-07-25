package com.example.votingsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vote_item")
@Data
public class VoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}