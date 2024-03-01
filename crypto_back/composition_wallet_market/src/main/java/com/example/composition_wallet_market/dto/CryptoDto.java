package com.example.composition_wallet_market.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CryptoDto {

    private String id;
    private String name;
    private String symbol;
    private double performance;


}
