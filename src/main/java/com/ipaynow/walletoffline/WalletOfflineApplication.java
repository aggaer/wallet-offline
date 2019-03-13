package com.ipaynow.walletoffline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = {"classpath:yishouyun-order-consumer.xml"})
public class WalletOfflineApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletOfflineApplication.class, args);
    }

}
