package com.example.market.repository;

import com.example.market.model.BuyerPost;
import com.example.market.model.MarketPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerPostRepository extends JpaRepository<BuyerPost, Long>, JpaSpecificationExecutor<BuyerPost> {
}
