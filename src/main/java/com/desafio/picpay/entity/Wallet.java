package com.desafio.picpay.entity;

import com.desafio.picpay.enumerated.WalletType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WALLETS")
public class Wallet {

    @Id
    private Long id;

    private Long fullName;

    private Long cpf;

    private String email;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private WalletType type;

    private BigDecimal balance;
}
