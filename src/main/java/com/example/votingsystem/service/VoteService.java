package com.example.votingsystem.service;

import com.example.votingsystem.entity.VoteItem;
import com.example.votingsystem.repository.VoteItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteService {

    @PersistenceContext
    private EntityManager entityManager;

    private final VoteItemRepository voteItemRepository;

    public VoteService(VoteItemRepository voteItemRepository) {
        this.voteItemRepository = voteItemRepository;
    }

    @Transactional
    public void addVoteItem(String name) {
        // 清理輸入，防止 XSS
        String cleanName = Jsoup.clean(name, Safelist.basic());
        if (cleanName.isEmpty() || cleanName.length() > 100) {
            throw new IllegalArgumentException("投票項目名稱無效或過長");
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("add_vote_item");
        query.registerStoredProcedureParameter("item_name", String.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("item_name", cleanName);
        query.execute();
    }

    @Transactional
    public void voteForItem(String voterName, List<Integer> itemIds) {
        // 清理 voterName，防止 XSS
        String cleanVoterName = Jsoup.clean(voterName, Safelist.none());
        if (cleanVoterName.isEmpty() || cleanVoterName.length() > 100) {
            throw new IllegalArgumentException("投票者名稱無效或過長");
        }
        for (Integer itemId : itemIds) {
            if (itemId == null || itemId <= 0) {
                throw new IllegalArgumentException("無效的投票項目 ID");
            }
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("vote_for_item");
            query.registerStoredProcedureParameter("voter", String.class, jakarta.persistence.ParameterMode.IN);
            query.registerStoredProcedureParameter("item_id", Integer.class, jakarta.persistence.ParameterMode.IN);
            query.setParameter("voter", cleanVoterName);
            query.setParameter("item_id", itemId);
            query.execute();
        }
    }

    @Transactional
    public void deleteVoteItem(Integer itemId) {
        if (itemId == null || itemId <= 0) {
            throw new IllegalArgumentException("無效的投票項目 ID");
        }
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("delete_vote_item");
        query.registerStoredProcedureParameter("item_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("item_id", itemId);
        query.execute();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getVoteResults() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("get_vote_results");
        return query.getResultList();
    }

    public List<VoteItem> getAllVoteItems() {
        return voteItemRepository.findAll();
    }
}