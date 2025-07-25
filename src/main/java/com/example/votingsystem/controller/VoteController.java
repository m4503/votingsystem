package com.example.votingsystem.controller;

import com.example.votingsystem.entity.VoteItem;
import com.example.votingsystem.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/votes")
    public ResponseEntity<List<Map<String, Object>>> getVoteResults() {
        List<Object[]> results = voteService.getVoteResults();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", row[0]);
            map.put("name", row[1]);
            map.put("voteCount", row[2]);
            response.add(map);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/vote")
    public ResponseEntity<String> voteForItem(@RequestParam String voterName, @RequestParam List<Integer> itemIds) {
        try {
            voteService.voteForItem(voterName, itemIds);
            return ResponseEntity.ok("投票成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/admin/items")
    public ResponseEntity<List<VoteItem>> getAllItems() {
        return ResponseEntity.ok(voteService.getAllVoteItems());
    }

    @PostMapping("/admin/items")
    public ResponseEntity<String> addVoteItem(@RequestParam String name) {
        try {
            voteService.addVoteItem(name);
            return ResponseEntity.ok("新增投票項目成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/admin/items/{id}")
    public ResponseEntity<String> deleteVoteItem(@PathVariable Integer id) {
        try {
            voteService.deleteVoteItem(id);
            return ResponseEntity.ok("刪除投票項目成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}