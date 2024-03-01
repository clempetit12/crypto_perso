package com.example.market_api.repository;

import com.example.market_api.entity.Crypto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


public interface CryptoRepository extends ReactiveMongoRepository<Crypto, Integer> {

Mono<Crypto> findCryptoByName(String crypto);
Mono<Crypto> findById(String id);

}






