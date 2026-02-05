package com.example.buyer.buypost.service;

//import com.example.seller.repository.PostFileRepository; // import

import com.example.buyer.buypost.dto.BuyerPostResponseDto;
import com.example.buyer.buypost.repository.BuyerPostRepository;

import com.example.buyer.mypage.model.User;
import com.example.buyer.mypage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuyerPostService {

    private final BuyerPostRepository buyerPostRepository;
    // 문자열 ID로 실제 회원의 PK(Long)를 찾기 위해 주입
    private final UserRepository userRepository;

    // 파일 저장을 위해 필요
//    private final PostFileRepository postFileRepository;
//
//    private final String uploadPath = "C:/eco_images/";

    @Transactional(readOnly = true)
    public List<BuyerPostResponseDto> getPostsBySellComId(String sellComId) {

        // 1. 문자열 ID("solar123")로 회원 엔티티 찾기
        User member = userRepository.findBySellComId(sellComId)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다. ID: " + sellComId));

        // 2. 엔티티에서 진짜 PK(Long) 꺼내기
        // (변수명을 realSellerId -> realBuyerId로 변경하여 혼동 방지)
        Long realBuyerId = member.getId();

        // 3. [핵심 수정] 아래에 정의한 'getPostsByBuyerId'를 호출해야 합니다!
        return getPostsByBuyerId(realBuyerId);
    }

    // ✅ 메서드 이름과 호출하는 레포지토리 메서드 변경
    public List<BuyerPostResponseDto> getPostsByBuyerId(Long buyerId) {
        // findByBuyerIdAndDelYn 으로 변경!
        return buyerPostRepository.findByBuyerIdAndDelYn(buyerId, "N").stream()
                .map(BuyerPostResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

}