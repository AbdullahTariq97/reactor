package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07Timeout {
    public static void main(String[] args) {
        getOrderNumbers()
                .timeout(Duration.ofSeconds(2), fallBack()) // this says the the subscriber will wait maximum of 2 secs for items to be emitted
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

        // when ever we use delay, things will get scheduled in a seperate thread
        // it will go into the non-blocking

        // the subscriber went with the fallback publisher

    }

    private static Flux<Integer> getOrderNumbers(){
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(5)); // no elements will be emitted before 5 seconds
    }

    private static Flux<Integer> fallBack(){
        return Flux.range(100,10)
                .delayElements(Duration.ofMillis(200));
    }
}
