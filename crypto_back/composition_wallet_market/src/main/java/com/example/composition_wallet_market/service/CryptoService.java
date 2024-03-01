package com.example.composition_wallet_market.service;

import com.example.composition_wallet_market.dto.CryptoDto;
import com.example.composition_wallet_market.dto.WalletDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CryptoService {


    private final WebClient webClient;


    public CryptoService() {
        this.webClient = WebClient.builder().baseUrl("http://10.125.51.56:8080").build();
    }

    public Flux<CryptoDto> getPrice(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        LocalDateTime dateConverted = LocalDateTime.parse(date, formatter);

        return WebClient.create()
                .get()
                .uri("/prices?date={date}", dateConverted)
                .retrieve()
                .bodyToFlux(CryptoDto.class);
    }

}
