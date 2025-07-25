package com.example.votingsystem.repository;

import com.example.votingsystem.entity.VoteRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRecordRepository extends JpaRepository<VoteRecord, Integer> {
}