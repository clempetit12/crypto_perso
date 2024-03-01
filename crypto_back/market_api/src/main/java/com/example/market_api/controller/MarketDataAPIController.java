package com.example.market_api.controller;

import com.example.market_api.entity.Crypto;
import com.example.market_api.entity.MarketData;
import com.example.market_api.repository.MarketDataRepository;
import com.example.market_api.service.CryptoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@RequestMapping("marketData")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MarketDataAPIController {

    private final Random random;

    private final MarketDataRepository marketDataRepository;

    private final CryptoService cryptoService;
    private LocalDateTime currentTradingTime = LocalDateTime.now();

    public MarketDataAPIController(MarketDataRepository marketDataRepository, CryptoService cryptoService) {
        this.marketDataRepository = marketDataRepository;
        this.cryptoService = cryptoService;

        random = new Random();
    }

    @PostMapping()
    public Mono<MarketData> addMarketData(@RequestBody MarketData marketData) {
        marketData.setTradingTime(currentTradingTime);
        currentTradingTime = currentTradingTime.plusDays(1);
        double cryptoValue = generateCryptoValue(marketData.getCryptoId());
        marketData.setCryptoValue(cryptoValue);
        return marketDataRepository.save(marketData);
    }

    private double generateCryptoValue(String cryptoId) {
        return Math.random() * 1000;

    }

    @GetMapping("/marketData/{cryptoId}")
    public Flux<MarketData> getMarketData(@PathVariable String cryptoId) {
        return marketDataRepository.findAllById(cryptoId);
    }

    @GetMapping("/all")
    public Flux<MarketData> getAll() {
        return marketDataRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<MarketData> getById(@PathVariable("id") String id){
        return marketDataRepository.findByCryptoId(id);
    }

}

