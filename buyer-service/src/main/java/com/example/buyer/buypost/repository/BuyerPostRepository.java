package com.example.buyer.buypost.repository; // [수정] 패키지명 seller

import com.example.buyer.buypost.model.BuyerPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerPostRepository extends JpaRepository<BuyerPost, Long> {
    // ✅ buyerId(구매자 ID)로 찾아야 합니다.
    List<BuyerPost> findByBuyerIdAndDelYn(Long buyerId, String delYn);
}