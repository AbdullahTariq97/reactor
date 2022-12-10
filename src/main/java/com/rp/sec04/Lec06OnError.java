package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06OnError {
    public static void main(String[] args) {
        // we want a fall back value

        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i) )
//                .onErrorReturn(-1)
//                .onErrorResume(e -> fallBack()
                .onErrorContinue((err,obj) -> {})
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallBack(){
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100,200));
    }
}
