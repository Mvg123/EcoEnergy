package com.example.buyer.buypost.controller;

import com.example.buyer.buypost.dto.BuyerPostResponseDto;
import com.example.buyer.buypost.service.BuyerPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/buyer")
@RequiredArgsConstructor
public class BuyerPostController {

    private final BuyerPostService buyerPostService;

    // 내 판매글 관리 목록 조회
    @GetMapping("/buyerpost")
    public String buyerPostPage(
            Model model,
            // 게이트웨이에서 넘겨준 문자열 ID를 받습니다.
            @RequestHeader(value = "X-USER-ID", required = false) String userId
    ) {
        // 로컬 테스트 등 헤더가 없을 땐 테스트 계정 ID 사용
        if (userId == null) {
            userId = "testUser"; // 테스트용 문자열 ID (DB에 실제 존재하는 ID여야 함)
        }

        // 서비스로 문자열 ID를 넘깁니다. (기존 getPostsBySellerId 대신 호출)
        List<BuyerPostResponseDto> posts = buyerPostService.getPostsBySellComId(userId);

        model.addAttribute("posts", posts);
        return "buyerpost";
    }
}