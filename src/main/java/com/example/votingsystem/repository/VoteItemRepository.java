package com.example.votingsystem.repository;

import com.example.votingsystem.entity.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteItemRepository extends JpaRepository<VoteItem, Integer> {
}