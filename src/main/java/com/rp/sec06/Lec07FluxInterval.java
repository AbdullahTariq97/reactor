package com.rp.sec06;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07FluxInterval {
    public static void main(String[] args) {
        // why do you get no console output when you execute this?
        // internally flux uses threads from parallel thread pool
        // when you block the main thread, you wait for this parallel thread to finish

        // you cannot override the thread pool you are using for the interval method

        System.out.println("Thread name: " + Thread.currentThread().getName());
        Flux.interval(Duration.ofSeconds(1))
                .doOnNext((item) -> System.out.println("Thread name: " + Thread.currentThread().getName()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }
}
