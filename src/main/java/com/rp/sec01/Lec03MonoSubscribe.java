package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    public static void main(String[] args) {
        // publisher
        // you can add further chaining operator to the end of the data being emitted from the publisher
        Mono<Integer> mono = Mono.just("ball")
                .map(String::length)
                .map(l -> l/1);

        // 1
        // makes the publisher emit data to the subscriber
//        mono.subscribe();

        // 2
        mono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

    }
}
