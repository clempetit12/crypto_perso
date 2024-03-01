package com.example.market_api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "marketData")
public class MarketData {

    @Id
    private String id;
    private LocalDateTime tradingTime;
    private double cryptoValue;
    @Column("crypto_id")
    private String cryptoId;

    public MarketData(LocalDateTime tradingTime, double cryptoValue, String cryptoId) {
        this.id = UUID.randomUUID().toString();
        this.tradingTime = tradingTime;
        this.cryptoValue = cryptoValue;
        this.cryptoId = cryptoId;
    }
}
