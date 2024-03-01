package com.example.market_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "performanceCrypto")
public class PerformanceResult {
    private String cryptoId;
    private double performance;


    }
