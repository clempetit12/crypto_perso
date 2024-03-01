package com.example.composition_wallet_market.Controller;

import com.example.composition_wallet_market.dto.CryptoDto;
import com.example.composition_wallet_market.dto.WalletDto;
import com.example.composition_wallet_market.service.CryptoService;
import com.example.composition_wallet_market.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class CompositionController {

private final WalletService walletService;
private final CryptoService cryptoService;


@Autowired
    public CompositionController(WalletService walletService, CryptoService cryptoService) {
        this.walletService = walletService;
        this.cryptoService = cryptoService;
    }

    @GetMapping("/wallet/{walletId}/cryptos/{date}")
    public Flux<CryptoDto> getCryptosForWallet(@PathVariable String walletId,@PathVariable String date) {
        Mono<WalletDto> walletMono = walletService.getWalletById(walletId);
        return walletMono.flatMapMany(wallet ->
                cryptoService.getPrice(date)
        );
    }




}
