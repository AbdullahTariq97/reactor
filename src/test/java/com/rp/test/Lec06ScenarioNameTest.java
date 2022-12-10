package com.rp.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec06ScenarioNameTest {

    @Test
    public void shouldOutputScenarioInformation() {
        Flux<String> just = Flux.just("a", "b", "c");

        StepVerifierOptions stepVerifierOptions = StepVerifierOptions.create().scenarioName("Alphabet-test");

        StepVerifier.create(just, stepVerifierOptions)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    public void shouldOutputScenarioInformation2() {
        Flux<String> just = Flux.just("a", "b", "c");

        StepVerifier.create(just)
                .expectNext("a")
                .as("a-test")
                .expectNext("b1")
                .as("b-test")
                .expectNext("c")
                .as("c-test")
                .verifyComplete();
    }
}
