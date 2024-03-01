package com.example.composition_wallet_market.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WalletDto {
    private long id;
    private double balance;
    private long userId;

}
