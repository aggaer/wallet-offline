package com.ipaynow.walletoffline;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @Author Jerry
 * @Date 2019/3/21 10:40
 **/
public class OptionalTest {
    public static void main(String[] args) throws InterruptedException {
        Instant startTime = Instant.now();
        while (true) {
            long seconds = Duration.between(startTime, Instant.now()).getSeconds();
            if (seconds >= 10) {
                System.out.println("exit");
                return;
            }
            TimeUnit.SECONDS.sleep(2);
            System.out.println("time go sec:" + seconds);
        }
    }

}
