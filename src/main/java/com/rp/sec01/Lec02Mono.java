package com.rp.sec01;

import reactor.core.publisher.Mono;

public class Lec02Mono {

    public static void main(String[] args) {
        // Publisher
        Mono<Integer> mono = Mono.just(1);

        // Rule: Nothing happens until you subscribe. Nothing gets emitted
        System.out.println(mono);

        // We subscribed therefore something happens
        mono.subscribe((i) -> System.out.println("Received: " + i));

    }
}
