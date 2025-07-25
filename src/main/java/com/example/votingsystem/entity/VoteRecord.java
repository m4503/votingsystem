package com.example.votingsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vote_record")
@Data
public class VoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "voter_name")
    private String voterName;

    @ManyToOne
    @JoinColumn(name = "vote_item_id")
    private VoteItem voteItem;
}