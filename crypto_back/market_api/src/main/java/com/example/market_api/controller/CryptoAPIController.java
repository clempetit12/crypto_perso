package com.example.market_api.controller;

import com.example.market_api.entity.Crypto;
import com.example.market_api.entity.MarketData;
import com.example.market_api.entity.PerformanceResult;
import com.example.market_api.repository.CryptoRepository;
import com.example.market_api.repository.MarketDataRepository;
import com.example.market_api.service.CryptoService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.enterprise.inject.Produces;
import java.util.List;

@RequestMapping("crypto")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CryptoAPIController {

    private final CryptoService cryptoService;
    private final CryptoRepository cryptoRepository;

    public CryptoAPIController(CryptoService cryptoService, CryptoRepository cryptoRepository) {
        this.cryptoService = cryptoService;
        this.cryptoRepository = cryptoRepository;
    }


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Crypto> getAllCrypto() {
        return cryptoRepository.findAll();
    }

    @PostMapping
    public Mono<Crypto> add(@RequestBody Crypto crypto) {
        return cryptoRepository.save(crypto);
    }

    @GetMapping(value = "/performance/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<PerformanceResult>>> calculatePerformance(@PathVariable("date") String date) {
        return cryptoService.calculatePerformanceForDate(date)
                .collectList()
                .map(performanceList -> {
                    if (!performanceList.isEmpty()) {
                        return ResponseEntity.ok().body(performanceList);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                });
    }



    @GetMapping("/{id}")
    public Mono<Crypto> getById(@PathVariable("id") String id){
        return cryptoRepository.findById(id);
    }

    @GetMapping(value = "/price/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Crypto> getPrice(@PathVariable String date) {
        return cryptoService.getPriceByDate(date);
    }
}
