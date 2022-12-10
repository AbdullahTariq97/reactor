package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec05SVVirtualTimeTest {
    // for flux that takes a long time to emmit elements
    // the tests for them can run for very long
    // to solve this you can use virtual timm method from step verifier
    // it simulates that the time required for the elements to be emitted has elapsed

    @Test
    public void test1(){
        StepVerifier.withVirtualTime(() -> timeConsumingFlux())
                .thenAwait(Duration.ofSeconds(30)) // how much time you want to simulate as virtual time
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }

    @Test
    public void first4SecondsNothingShouldBeHappening(){
        StepVerifier.withVirtualTime(() -> timeConsumingFlux())
                .expectSubscription() // sub is an event
                .expectNoEvent(Duration.ofSeconds(4))
                .thenAwait(Duration.ofSeconds(30))
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }

    @Test
    public Flux<String> timeConsumingFlux(){
        return Flux.range(1,4)
                .delayElements(Duration.ofSeconds(5))
                .map(integer -> integer + "a");
    }
}
