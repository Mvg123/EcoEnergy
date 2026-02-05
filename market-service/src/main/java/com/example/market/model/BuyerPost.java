package com.example.market.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "buyer_posts")
public class BuyerPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "BUYER_ID")
    private Long buyerId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ENERGY_TYPE")
    private String energyType;

    @Column(name = "LAND_TYPE")
    private String landType;

    @Column(name = "LAND_AREA")
    private Double landArea;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "LOCATION_DETAIL")
    private String locationDetail;

    @Column(name = "FACILITY_CAPACITY")
    private Integer facilityCapacity;

    @Column(name = "VOLUME_KWH")
    private Long volumeKwh;

    @Column(name = "VOLUME_REC")
    private Integer volumeRec;

    @Column(name = "WEIGHTING_FACTOR")
    private BigDecimal weightingFactor;

    @Column(name = "CONTRACT_TYPE")
    private String contractType;

    @Column(name = "CONTRACT_UNIT")
    private String contractUnit;

    @Column(name = "PRICE_KRW")
    private Long priceKrw;

    @Column(name = "IS_PRICE_NEGOTIABLE")
    @ColumnDefault("'N'")
    private String isPriceNegotiable;

    @Column(name = "CONTRACT_START_DATE")
    private LocalDate contractStartDate;

    @Column(name = "CONTRACT_END_DATE")
    private LocalDate contractEndDate;

    @Column(name = "IS_PERIOD_NEGOTIABLE")
    @ColumnDefault("'N'")
    private String isPeriodNegotiable;

    @Column(name = "CONTENT")
    private String content;

    // [삭제] private String imageUrl;

    @Column(name = "PURCHASE_STATUS")
    @ColumnDefault("'Y'")
    private String purchaseStatus; // Y:진행중, N:마감

    @Column(name = "DEL_YN")
    @ColumnDefault("'N'")
    private String delYn;

    @Column(name = "REG_DATE")
    private LocalDateTime regDate;

    // 게시글 수정 메서드 (이미지 인자 제거됨)
    public void update(String title, String energyType, String landType, Double landArea,
                       String location, String locationDetail, Integer facilityCapacity,
                       BigDecimal weightingFactor, Long volumeKwh, Integer volumeRec,
                       String contractType, String contractUnit, Long priceKrw,
                       String isPriceNegotiable, LocalDate contractStartDate,
                       LocalDate contractEndDate, String isPeriodNegotiable,
                       String content) {

        this.title = title;
        this.energyType = energyType;
        this.landType = landType;
        this.landArea = landArea;
        this.location = location;
        this.locationDetail = locationDetail;
        this.facilityCapacity = facilityCapacity;
        this.weightingFactor = weightingFactor;
        this.volumeKwh = volumeKwh;
        this.volumeRec = volumeRec;
        this.contractType = contractType;
        this.contractUnit = contractUnit;
        this.priceKrw = priceKrw;
        this.isPriceNegotiable = isPriceNegotiable;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.isPeriodNegotiable = isPeriodNegotiable;
        this.content = content;

        // [삭제] 이미지 업데이트 로직 제거
    }

    // 삭제 처리
    public void delete() {
        this.delYn = "Y";
        this.purchaseStatus = "N";
    }
}