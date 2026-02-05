package com.example.buyer.mypage.repository;

import com.example.buyer.mypage.model.SubManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubManagerRepository extends JpaRepository<SubManager, Long> {
    // SellerId -> UserId 로 변경
    List<SubManager> findByUserIdAndDelYn(Long userId, String delYn);
}