package com.example.market_api.repository;

import com.example.market_api.entity.Crypto;
import com.example.market_api.entity.MarketData;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;


public interface MarketDataRepository extends ReactiveMongoRepository<MarketData, Integer> {

    Flux<MarketData> findAllById(String id);

    Mono<MarketData> findFirstByCryptoIdAndTradingTimeGreaterThanEqualOrderByTradingTimeAsc(String cryptoId, LocalDate dateTime);

    Mono<MarketData> findFirstByCryptoIdAndTradingTimeLessThanOrderByTradingTimeDesc(LocalDate dateTime, String id);

    Flux<MarketData> findByTradingTime(LocalDate dateTime);

    Mono<MarketData> findByCryptoId(String id);
}
