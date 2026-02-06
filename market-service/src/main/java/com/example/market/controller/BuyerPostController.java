package com.example.market.controller;

import com.example.market.dto.BuyerPostRequestDto;
import com.example.market.dto.BuyerPostResponseDto;
import com.example.market.dto.BuyerPostSearchDto;
import com.example.market.repository.UserRepository;
import com.example.market.service.BuyerPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BuyerPostController {

    private final BuyerPostService buyerPostService;
    private final UserRepository userRepository;

    // 목록 조회 -> buyerpost.html
    @GetMapping("/market/buyerpost")
    public String buyerList(Model model,
                            @RequestParam(value="page", defaultValue="0") int page,
                            @RequestParam(value="sort", defaultValue="latest") String sort,
                            @ModelAttribute BuyerPostSearchDto searchDto,
                            @RequestHeader(value = "X-USER-ID", required = false) String sellComId) {

        Page<BuyerPostResponseDto> paging = buyerPostService.getPaging(page, sort, searchDto);

        // [중요] paging 객체와 함께, 셀러 페이지와 형식을 맞추기 위해 posts 리스트를 직접 담습니다.
        model.addAttribute("paging", paging);
        model.addAttribute("sort", sort);
        model.addAttribute("searchDto", searchDto);

        // 로그인한 사용자 정보 조회해서 모델에 담기
        if (sellComId != null) {
            userRepository.findBySellComId(sellComId).ifPresent(user -> {
                model.addAttribute("user", user); // user 객체 전체를 넘김
            });
        }

        return "buyerpost";
    }

    // 작성 페이지 -> buyerpost_write.html
    @GetMapping("/market/buyer/write")
    public String writePage() {
        return "buyerpost_write"; // [수정] 파일명 일치
    }

    // 작성 완료
    @PostMapping("/market/buyer/write")
    public String createPost(
            @RequestHeader(value = "X-USER-ID", required = false) String userId,
            @ModelAttribute BuyerPostRequestDto dto) {

        if (userId == null) userId = "testUser";
        buyerPostService.createPost(dto, userId);

        return "redirect:/market/buyerpost";
    }

    // 상세 조회 -> buyerpost_detail.html
    @GetMapping("/market/buyer/post/{id}")
    public String postDetail(@PathVariable Long id,
                             @RequestHeader(value = "X-USER-ID", required = false) String userId,
                             Model model) {

        BuyerPostResponseDto post = buyerPostService.getPostById(id);
        model.addAttribute("post", post);

        if (post.getBuyerId() != null) {
            userRepository.findById(post.getBuyerId()).ifPresent(buyer -> {
                model.addAttribute("buyerName", buyer.getSellComName());
            });
        }

        if (userId != null) {
            userRepository.findBySellComId(userId).ifPresent(user -> {
                model.addAttribute("user", user);
            });
        }

        return "buyerpost_detail"; // [수정] 파일명 일치
    }

    // 수정 페이지 -> buyerpost_edit.html
    @GetMapping("/market/buyer/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        BuyerPostResponseDto post = buyerPostService.getPostById(id);
        model.addAttribute("post", post);
        return "buyerpost_edit"; // [수정] 파일명 일치
    }

    // 수정 완료
    @PostMapping("/market/buyer/edit/{id}")
    public String updatePost(
            @PathVariable Long id,
            @RequestHeader(value = "X-USER-ID", required = false) String userId,
            @ModelAttribute BuyerPostRequestDto dto) {

        if (userId == null) userId = "testUser";
        buyerPostService.updatePost(id, userId, dto);
        return "redirect:/market/buyerpost"; // 목록으로 리다이렉트
    }

    // 삭제
    @PostMapping("/market/buyer/delete/{id}")
    public String deletePost(
            @PathVariable Long id,
            @RequestHeader(value = "X-USER-ID", required = false) String userId) {

        if (userId == null) userId = "testUser";
        buyerPostService.deletePost(id, userId);
        return "redirect:/market/buyerpost";
    }

    // 상태 변경
    @PostMapping("/market/buyer/status/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        buyerPostService.updateStatus(id, status);
        return "redirect:/buyer/buyerpost";
    }
}