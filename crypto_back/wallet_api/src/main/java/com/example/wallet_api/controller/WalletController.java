package com.example.wallet_api.controller;


import com.example.wallet_api.entity.Wallet;
import com.example.wallet_api.service.WalletService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("wallet")
public class WalletController {


// post pour achat, delete une quantity, get all et id

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }


    @GetMapping("/{walletId}")
    public Mono<Wallet> getWalletById(@PathVariable("walletId") Long walletId){
        return walletService.getWalletById(walletId);
    }

    @GetMapping()
    public Flux<Wallet> getAllWallet(){
        return walletService.getAll();
    }

    @DeleteMapping("/{walletId}")
    public Mono<Void> deleteAWallet(@PathVariable("walletId") Long walletId){
        return walletService.deleteWallet(walletId);
    }




}
