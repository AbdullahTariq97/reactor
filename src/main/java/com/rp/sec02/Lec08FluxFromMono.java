package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec08FluxFromMono {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("a");
        
        Flux<String> flux = Flux.from(mono);

        flux.subscribe(Util.onNext());


        // next method can be called off a flux object to return a mono
        Flux.range(1, 10).filter(i -> i > 3).next().subscribe(Util.onNext());

    }

    // this might be required if a method requires flux input, but you have a mono
    private static void doSomething(Flux<String> flux){


    }}
