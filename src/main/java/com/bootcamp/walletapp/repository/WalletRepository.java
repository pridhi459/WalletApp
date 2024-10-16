package com.bootcamp.walletapp.repository;

import com.bootcamp.walletapp.entity.User;
import com.bootcamp.walletapp.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
  //  Wallet findByUserId(long UserId);
    Wallet findByWalletId(long walletId);
}
