package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec02SVErrorClass {

    @Test
    public void test1(){
        Flux<Integer> just = Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1));

        Flux<Integer> error = Flux.error(() -> new RuntimeException("oops"));

        Flux<Integer> concat = Flux.concat(just, error);

        // step verifier will internally subscriber to the flux and help you test
        StepVerifier.create(concat)
                .expectNext(1,2,3)
                .verifyError();
    }

    @Test
    public void test2(){
        Flux<Integer> just = Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1));

        Flux<Integer> error = Flux.error(() -> new RuntimeException("oops"));

        Flux<Integer> concat = Flux.concat(just, error);

        // step verifier will internally subscriber to the flux and help you test
        StepVerifier.create(concat)
                .expectNext(1,2,3)
                .verifyError(RuntimeException.class);
    }

    @Test
    public void test3(){
        Flux<Integer> just = Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1));

        Flux<Integer> error = Flux.error(() -> new RuntimeException("oops"));

        Flux<Integer> concat = Flux.concat(just, error);

        // step verifier will internally subscriber to the flux and help you test
        StepVerifier.create(concat)
                .expectNext(1,2,3)
                .verifyErrorMessage("oops");
    }
}
