package com.example.buyer.mypage.repository;

import com.example.buyer.mypage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySellComId(String sellComId);
}