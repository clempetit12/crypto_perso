package com.example.composition_wallet_market.service;

import com.example.composition_wallet_market.dto.WalletDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@Service
public class WalletService {


    private final WebClient webClient;


    public WalletService() {
        this.webClient = WebClient.builder().baseUrl("http://10.125.51.56:8080").build();
    }

    public Mono<WalletDto> getWalletById(String walletId) {
        return webClient.get()
                .uri("/{walletId}", walletId)
                .retrieve()
                .bodyToMono(WalletDto.class);
    }


}
