package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec09FluxInterval {

    public static void main(String[] args) {

        // useful when you want to push some updates to the end user
        // like stock price updates
        Flux.interval(Duration.ofSeconds(1)).subscribe(Util.onNext());
    }

}
