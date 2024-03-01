package com.example.wallet_api.repository;

import com.example.wallet_api.entity.Wallet;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends R2dbcRepository<Wallet,Long> {
}
