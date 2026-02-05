package com.example.market.dto;

import com.example.market.model.BuyerPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyerPostResponseDto {
    private Long id;
    private Long buyerId;
    private String title;
    private String content;
    private String energyType;

    // 부지 정보
    private String landType;
    private Double landArea;
    private String location;
    private String locationDetail;

    // 설비 정보
    private Integer facilityCapacity;
    private BigDecimal weightingFactor;
    private Long volumeKwh;
    private Integer volumeRec;

    // 계약 정보
    private String contractType;
    private String contractUnit;

    // 가격 및 기간
    private Long priceKrw;
    private String isPriceNegotiable;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private String isPeriodNegotiable;

    // 상태
    private String status;
    private LocalDateTime regDate;
    // [삭제] private String imageUrl;

    public static BuyerPostResponseDto fromEntity(BuyerPost entity) {

        // [삭제] 이미지 경로 처리 로직 전체 제거

        return BuyerPostResponseDto.builder()
                .id(entity.getPostId())
                .buyerId(entity.getBuyerId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .energyType(entity.getEnergyType())
                .landType(entity.getLandType())
                .landArea(entity.getLandArea())
                .location(entity.getLocation())
                .locationDetail(entity.getLocationDetail())
                .facilityCapacity(entity.getFacilityCapacity())
                .weightingFactor(entity.getWeightingFactor())
                .volumeKwh(entity.getVolumeKwh())
                .volumeRec(entity.getVolumeRec())
                .contractType(entity.getContractType())
                .contractUnit(entity.getContractUnit())
                .priceKrw(entity.getPriceKrw())
                .isPriceNegotiable(entity.getIsPriceNegotiable())
                .contractStartDate(entity.getContractStartDate())
                .contractEndDate(entity.getContractEndDate())
                .isPeriodNegotiable(entity.getIsPeriodNegotiable())
                .regDate(entity.getRegDate())

                // [삭제] .imageUrl(fullPath)

                .status("Y".equals(entity.getPurchaseStatus()) ? "진행중" : "마감")
                .build();
    }
}