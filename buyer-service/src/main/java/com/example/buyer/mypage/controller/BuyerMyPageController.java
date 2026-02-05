package com.example.buyer.mypage.controller;

import com.example.buyer.mypage.dto.CorporateMemberResponseDto;
import com.example.buyer.mypage.dto.PasswordUpdateDto;
import com.example.buyer.mypage.model.SubManager;
import com.example.buyer.mypage.repository.UserRepository;
import com.example.buyer.mypage.service.BuyerMyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BuyerMyPageController {

    private final BuyerMyPageService buyerMyPageService;

    private void checkBuyerAuthority(String role) {
        // AuthorizationFilter에서 넘겨준 X-USER-ROLE 값이 "BUYER"인지 확인
        if (!"BUYER".equals(role)) {
            // 게이트웨이 1차 방어 후, 컨트롤러에서 2차 검증
            throw new IllegalArgumentException("접근 권한이 없습니다. (구매자 전용)");
        }
    }

    @GetMapping("/buyer/profile")
    public String profile(Model model,
                          @RequestHeader(value = "X-USER-ID", required = false) String sellComId,
                          @RequestHeader(value = "X-USER-ROLE", required = false) String role) {
        // 로그인 여부 확인
        if (sellComId == null) {
            return "redirect:/login";
        }

        // 판매자 권한 확인 (구매자가 URL로 직접 들어오는 것 방지)
        checkBuyerAuthority(role);

        // 서비스 호출
        CorporateMemberResponseDto myInfo = buyerMyPageService.getMyInfo(sellComId);
        model.addAttribute("info", myInfo);

        // 담당자 목록 조회
        List<SubManager> managers = buyerMyPageService.getManagers(sellComId);
        model.addAttribute("managers", managers);

        return "profile";
    }

    // 담당자 추가
    @PostMapping("/buyer/manager/add")
    public String addManager(@RequestHeader("X-USER-ID") String sellComId,
                             @RequestHeader("X-USER-ROLE") String role, // role 추가
                             @RequestParam("managerName") String name,
                             @RequestParam String email,
                             @RequestParam String department,
                             @RequestParam String phone) {

        checkBuyerAuthority(role); // 권한 체크
        buyerMyPageService.addManager(sellComId, name, email, department, phone);

        return "redirect:/buyer/profile";
    }

    // 담당자 수정
    @PostMapping("/buyer/manager/update/{id}")
    public String updateManager(@RequestHeader("X-USER-ID") String sellComId,
                                @RequestHeader("X-USER-ROLE") String role, // role 추가
                                @PathVariable Long id,
                                @RequestParam String managerName,
                                @RequestParam String department,
                                @RequestParam String phone,
                                @RequestParam String email) {

        checkBuyerAuthority(role); // 권한 체크
        buyerMyPageService.updateManager(sellComId, id, managerName, email, department, phone);

        return "redirect:/buyer/profile";
    }

    // 담당자 삭제
    @PostMapping("/buyer/manager/delete/{id}")
    public String deleteManager(@RequestHeader("X-USER-ID") String sellComId,
                                @RequestHeader("X-USER-ROLE") String role, // role 추가
                                @PathVariable Long id) {

        checkBuyerAuthority(role); // 권한 체크
        buyerMyPageService.deleteManager(sellComId, id);

        return "redirect:/buyer/profile";
    }

    // 비밀번호 변경 페이지
    @GetMapping("/buyer/passwordUpdate")
    public String passwordUpdatePage(@RequestHeader(value = "X-USER-ROLE", required = false) String role) {
        checkBuyerAuthority(role); // 페이지 진입 시에도 체크
        return "passwordUpdate";
    }

    // 비밀번호 변경 요청 처리
    @PostMapping("/buyer/updatePassword")
    public String updatePassword(@RequestHeader("X-USER-ID") String sellComId,
                                 @RequestHeader("X-USER-ROLE") String role, // role 추가
                                 PasswordUpdateDto dto,
                                 RedirectAttributes redirectAttributes) {

        checkBuyerAuthority(role); // 권한 체크
        try {
            buyerMyPageService.changePassword(sellComId, dto);

            redirectAttributes.addFlashAttribute("msg", "비밀번호가 성공적으로 변경되었습니다.");
            return "redirect:/buyer/profile";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/buyer/passwordUpdate";
        }
    }

    // 회원 탈퇴 처리
    @PostMapping("/buyer/withdraw")
    public String withdraw(@RequestHeader("X-USER-ID") String sellComId,
                           @RequestHeader("X-USER-ROLE") String role) { // role 추가
        checkBuyerAuthority(role); // 권한 체크
        buyerMyPageService.withdrawSeller(sellComId);
        return "redirect:/buyer/marketpost";
    }
}