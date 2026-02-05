package com.example.buyer.buypost.dto;

import com.example.buyer.buypost.model.BuyerPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyerPostResponseDto {
    private Long id;
    private String title;
    private String energyType;
    private String status;
    private Long priceKrw;
    private String imageUrl;
    private LocalDateTime regDate;

    public static BuyerPostResponseDto fromEntity(BuyerPost entity) {

        return BuyerPostResponseDto.builder()
                .id(entity.getPostId())
                .title(entity.getTitle())
                .energyType(entity.getEnergyType())
                .priceKrw(entity.getPriceKrw())

                .regDate(entity.getRegDate())
                .status("Y".equals(entity.getPurchaseYN()) ? "판매중" : "판매종료")
                .build();
    }
}