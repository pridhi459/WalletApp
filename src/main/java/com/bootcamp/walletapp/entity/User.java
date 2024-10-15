package com.bootcamp.walletapp.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @JoinColumn(name="user_id", referencedColumnName = "user_user_id")
    private Long userId;

    @Column(name ="username", unique = true, nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="wallet_id", referencedColumnName = "wallet_id")
    private Wallet wallet;

    public User(String user, String password) {
        this.username = user;
        this.password = password;

        this.wallet=new Wallet(this);
       // System.out.println(wallet.getWalletId());
    }


}
