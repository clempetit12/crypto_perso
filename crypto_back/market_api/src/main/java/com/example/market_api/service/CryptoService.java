package com.example.market_api.service;

import com.example.market_api.entity.Crypto;
import com.example.market_api.entity.MarketData;
import com.example.market_api.entity.PerformanceResult;
import com.example.market_api.repository.CryptoRepository;
import com.example.market_api.repository.MarketDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CryptoService {

    @Autowired
    private final MarketDataRepository marketDataRepository;

    @Autowired
    private final CryptoRepository cryptoRepository;
//

    public CryptoService(CryptoRepository cryptoRepository, MarketDataRepository marketDataRepository, CryptoRepository cryptoRepository1) {
        this.marketDataRepository = marketDataRepository;
        this.cryptoRepository = cryptoRepository1;
    }


    public Flux<PerformanceResult> calculatePerformanceForDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateConverted = LocalDate.parse(date, formatter);

        Flux<String> cryptoIdsFlux = marketDataRepository.findAll().map(MarketData::getCryptoId).distinct();

        return cryptoIdsFlux.flatMap(idCrypto ->
                calculatePerformanceForCrypto(idCrypto, dateConverted)
                        .map(performance -> new PerformanceResult(idCrypto, performance))
        );
    }

    private Mono<Double> calculatePerformanceForCrypto(String idCrypto, LocalDate dateConverted) {
        Mono<MarketData> nearestPriceMono = marketDataRepository.findFirstByCryptoIdAndTradingTimeGreaterThanEqualOrderByTradingTimeAsc(idCrypto, dateConverted)
                .onErrorResume(error -> {
                    System.err.println("Error fetching nearest price for crypto " + idCrypto + ": " + error.getMessage());
                    return Mono.empty();
                });

        Mono<MarketData> latestPriceMono = marketDataRepository.findFirstByCryptoIdAndTradingTimeGreaterThanEqualOrderByTradingTimeAsc(idCrypto, dateConverted.minusDays(1))
                .onErrorResume(error -> {
                    System.err.println("Error fetching latest price for crypto " + idCrypto + ": " + error.getMessage());
                    return Mono.empty();
                });

        return Mono.zip(nearestPriceMono, latestPriceMono)
                .map(tuple -> {
                    MarketData nearestPrice = tuple.getT1();
                    MarketData latestPrice = tuple.getT2();
                    if (nearestPrice != null && latestPrice != null) {
                        double performance = Math.round(((latestPrice.getCryptoValue() - nearestPrice.getCryptoValue()) / nearestPrice.getCryptoValue()) * 100.0);
                        System.out.println("Performance calculated for crypto " + idCrypto + ": " + performance);
                        return performance;
                    } else {
                        System.err.println("Error: One or both prices are null for crypto " + idCrypto);
                        return 0.0;
                    }
                });
    }

    public Flux<Crypto> getPriceByDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateConverted = LocalDate.parse(date, formatter);

       return marketDataRepository.findByTradingTime(dateConverted)
               .map(this::mapToCrypto);
    }


    private Crypto mapToCrypto(MarketData marketData) {
        Crypto crypto = new Crypto();
        crypto.setId(marketData.getCryptoId());
        crypto.setPerformance(marketData.getCryptoValue());
        return crypto;
    }


    public Mono<Double> calculatePerformanceForDateForOneCrypto(String date, String cryptoId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateConverted = LocalDate.parse(date, formatter);

        Mono<MarketData> nearestPriceMono = marketDataRepository.findFirstByCryptoIdAndTradingTimeGreaterThanEqualOrderByTradingTimeAsc(cryptoId, dateConverted)
                .onErrorResume(error -> {
                    System.err.println("Error fetching nearest price for crypto " + cryptoId + ": " + error.getMessage());
                    return Mono.empty();
                })
                .flatMap(marketData -> {
                    double cryptoValue = marketData.getCryptoValue();
                    System.out.println("Crypto performance: " + cryptoValue);
                    return Mono.just(marketData);
                });

        Mono<MarketData> latestPriceMono = marketDataRepository.findFirstByCryptoIdAndTradingTimeGreaterThanEqualOrderByTradingTimeAsc(cryptoId, dateConverted.minusDays(1))
                .onErrorResume(error -> {
                    System.err.println("Error fetching latest price for crypto " + cryptoId + ": " + error.getMessage());
                    return Mono.empty();
                })
                .flatMap(marketData -> {
                    double cryptoValue = marketData.getCryptoValue();
                    return Mono.just(marketData);
                });

        Mono<Double> performanceMono = nearestPriceMono.zipWith(latestPriceMono)
                .map(tuple -> {
                    double nearestPrice = tuple.getT1().getCryptoValue();
                    double latestPrice = tuple.getT2().getCryptoValue();
                    double performance = ((latestPrice - nearestPrice) / nearestPrice) * 100.0;
                    return Math.round(performance * 100.0) / 100.0; // Arrondi à 2 décimales
                })
                .onErrorResume(error -> {
                    System.err.println("Error calculating performance: " + error.getMessage());
                    return Mono.empty();
                });

        return performanceMono;




    }

    public Mono<Double> getPriceByDateAndByCryptoId(String date, String cryptoId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateConverted = LocalDate.parse(date, formatter);
        Mono<Double> priceSearched = marketDataRepository.findFirstByCryptoIdAndTradingTimeGreaterThanEqualOrderByTradingTimeAsc( cryptoId, dateConverted)
                .map(marketData -> marketData.getCryptoValue())
                .onErrorResume(error -> {
                    System.err.println("Error fetching nearest price for crypto " + cryptoId + ": " + error.getMessage());
                    return Mono.empty();
                });
        System.out.println("price searched " + priceSearched);
return priceSearched;
    }
}





