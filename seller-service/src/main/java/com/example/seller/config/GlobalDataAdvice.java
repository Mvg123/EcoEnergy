package com.example.seller.config; // 패키지명은 본인 프로젝트에 맞게 수정

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalDataAdvice {

    // 모든 컨트롤러가 호출될 때 이 메서드가 먼저 실행됨
    // HTML(Thymeleaf)에서 ${userRole} 로 언제든 접근 가능해짐
    @ModelAttribute("userRole")
    public String populateUserRole(HttpServletRequest request) {
        // Gateway 필터(AuthorizationFilter)가 헤더에 넣어준 값을 꺼냄
        String role = request.getHeader("X-USER-ROLE");

        // 값이 없으면 빈 문자열 반환 (NullPointerException 방지)
        return (role != null) ? role : "";
    }

    // ID
    @ModelAttribute("userId")
    public String populateUserId(HttpServletRequest request) {
        String userId = request.getHeader("X-USER-ID");
        return (userId != null) ? userId : "";
    }
}