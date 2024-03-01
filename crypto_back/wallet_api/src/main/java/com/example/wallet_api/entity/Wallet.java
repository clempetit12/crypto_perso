package com.example.wallet_api.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Table("wallet")
public class Wallet {


    @Id
    private long id;
    private double balance;

    private User user;



}
