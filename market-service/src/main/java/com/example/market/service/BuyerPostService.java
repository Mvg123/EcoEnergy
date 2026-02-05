package com.example.market.service;

import com.example.market.dto.BuyerPostRequestDto;
import com.example.market.dto.BuyerPostResponseDto;
import com.example.market.dto.BuyerPostSearchDto;
import com.example.market.model.BuyerPost;
import com.example.market.model.User;
import com.example.market.repository.BuyerPostRepository;
import com.example.market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// [삭제] import org.springframework.web.multipart.MultipartFile;
// [삭제] java.io.File, IOException, UUID 임포트 등

@Service
@RequiredArgsConstructor
public class BuyerPostService {

    private final BuyerPostRepository buyerPostRepository;
    private final UserRepository userRepository;

    // [삭제] private final String uploadPath = ...;

    // 목록 조회 (변경 없음)
    @Transactional(readOnly = true)
    public Page<BuyerPostResponseDto> getPaging(int page, String sortStr, BuyerPostSearchDto searchDto) {

        Sort sort;
        switch (sortStr) {
            case "volume": sort = Sort.by(Sort.Direction.DESC, "volumeKwh"); break;
            case "priceHigh": sort = Sort.by(Sort.Direction.DESC, "priceKrw"); break;
            case "priceLow": sort = Sort.by(Sort.Direction.ASC, "priceKrw"); break;
            default: sort = Sort.by(Sort.Direction.DESC, "postId"); break;
        }
        Pageable pageable = PageRequest.of(page, 10, sort);

        Specification<BuyerPost> spec = BuyerPostSpecService.search(searchDto);
        Page<BuyerPost> postPage = buyerPostRepository.findAll(spec, pageable);

        return postPage.map(BuyerPostResponseDto::fromEntity);
    }

    // 상세 조회 (변경 없음)
    @Transactional(readOnly = true)
    public BuyerPostResponseDto getPostById(Long id) {
        BuyerPost post = buyerPostRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        return BuyerPostResponseDto.fromEntity(post);
    }

    // 등록
    @Transactional
    public void createPost(BuyerPostRequestDto dto, String userId) {

        User member = userRepository.findBySellComId(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원 찾을 수 없음: " + userId));
        Long buyerId = member.getId();

        // Entity 생성 (이미지 인자 없이 호출)
        BuyerPost buyerPost = dto.toEntity(buyerId);
        buyerPostRepository.save(buyerPost);
    }

    // 수정
    @Transactional
    public void updatePost(Long postId, String userId, BuyerPostRequestDto dto) {
        BuyerPost post = buyerPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        User member = userRepository.findBySellComId(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원 찾을 수 없음"));

        if (!post.getBuyerId().equals(member.getId())) {
            throw new IllegalArgumentException("수정 권한 없음");
        }

        // [삭제] 파일 처리 로직 제거

        post.update(
                dto.getTitle(), dto.getEnergyType(), dto.getLandType(), dto.getLandArea(),
                dto.getLocation(), dto.getLocationDetail(), dto.getFacilityCapacity(),
                dto.getWeightingFactor(), dto.getVolumeKwh(), dto.getVolumeRec(),
                dto.getContractType(), dto.getContractUnit(), dto.getPriceKrw(),
                "on".equals(dto.getIsPriceNegotiable()) ? "Y" : "N",
                dto.getContractStartDate(), dto.getContractEndDate(),
                "on".equals(dto.getIsPeriodNegotiable()) ? "Y" : "N",
                dto.getContent()
                // [삭제] , newFileName 인자 제거
        );
    }

    // 삭제 (변경 없음)
    @Transactional
    public void deletePost(Long postId, String userId) {
        BuyerPost post = buyerPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        User member = userRepository.findBySellComId(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원 찾을 수 없음"));

        if (!post.getBuyerId().equals(member.getId())) {
            throw new IllegalArgumentException("삭제 권한 없음");
        }

        post.delete();
    }

    // BuyerPostService.java에 추가해야 할 메서드
    @Transactional
    public void updateStatus(Long postId, String status) {
        // 1. 게시글 조회
        BuyerPost post = buyerPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + postId));
        post.setPurchaseStatus(status);
    }
}