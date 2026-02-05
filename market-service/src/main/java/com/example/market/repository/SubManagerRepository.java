package com.example.market.repository;

import com.example.market.model.SubManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubManagerRepository extends JpaRepository<SubManager, Long> {
    List<SubManager> findByUserIdAndDelYn(Long userId, String delYn);
}