package com.example.buyer.buypost.dto;

import com.example.buyer.buypost.model.BuyerPost;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BuyerPostRequestDto {
    private String title;
    private String energyType;
    private String landType;
    private Double landArea;
    private String location;
    private String locationDetail;
    private Integer facilityCapacity;
    private BigDecimal weightingFactor;
    private Long volumeKwh;
    private Integer volumeRec;
    private String contractType;
    private String contractUnit;
    private Long priceKrw;
    private String isPriceNegotiable;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private String isPeriodNegotiable;
    private String content;

    private MultipartFile imageFile;

    public BuyerPost toEntity(Long buyerId) {
        return BuyerPost.builder()
                .buyerId(buyerId)
                .title(this.title)
                .energyType(this.energyType)
                .landType(this.landType)
                .landArea(this.landArea)
                .location(this.location)
                .locationDetail(this.locationDetail)
                .facilityCapacity(this.facilityCapacity)
                .weightingFactor(this.weightingFactor)
                .volumeKwh(this.volumeKwh)
                .volumeRec(this.volumeRec)
                .contractType(this.contractType)
                .contractUnit(this.contractUnit)
                .priceKrw(this.priceKrw)
                .isPriceNegotiable("on".equals(this.isPriceNegotiable) ? "Y" : "N")
                .contractStartDate(this.contractStartDate)
                .contractEndDate(this.contractEndDate)
                .isPeriodNegotiable("on".equals(this.isPeriodNegotiable) ? "Y" : "N")
                .content(this.content)
                .purchaseYN("Y")
                .delYn("N")
                .regDate(LocalDateTime.now())
                .build();
    }
}