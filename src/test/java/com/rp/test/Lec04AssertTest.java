package com.rp.test;

import com.rp.sec03.helper.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec04AssertTest {
    @Test
    public void assertOnEmittedObject(){
        Mono<Person> personMono = Mono.fromSupplier(Person::new);

        StepVerifier.create(personMono)
                .assertNext(obj -> Assertions.assertNotNull(obj.getName()))
                .verifyComplete();
    }

    @Test
    public void shouldEmmitObjectWithinTimeLimit(){
        Mono<Person> personMono = Mono.fromSupplier(Person::new).delayElement(Duration.ofSeconds(2));

        StepVerifier.create(personMono)
                .assertNext(obj -> Assertions.assertNotNull(obj.getName()))
                .expectComplete()
                .verify(Duration.ofSeconds(3));
    }
}
