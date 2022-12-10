package com.rp.test;

import com.rp.courseutil.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec01SVDemoTest {

    @Test
    public void shouldReturnCorrectFlux(){
        Flux<Integer> just = Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1));

        // step verifier will internally subscriber to the flux and help you test
        StepVerifier.create(just)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectComplete()
                .verify();
    }

    @Test
    public void shouldReturnCorrectFlux2(){
        Flux<Integer> just = Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(1));

        // step verifier will internally subscriber to the flux and help you test
        StepVerifier.create(just)
                .expectNext(1,2,3)
                .verifyComplete();
    }


//    @Test
//    public void test(){
//        Mono.just("abdullah").map(name -> {
//            System.out.println("entering first map");
//            printCurrentThread();
//            return name;
//        }).map(name -> {
//            System.out.println("entering second map");
//            printCurrentThread();
//            return name;
//        }).subscribeOn(Schedulers.boundedElastic());
//    }

//    public static void printCurrentThread(){
//        System.out.println("Current Thread is: " + Thread.currentThread().getName());
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        Flux.create(monoSink -> {
//            System.out.println("creating");
//            monoSink.next("abdullah");
//        }).map(name -> {
//            System.out.println("entering first map");
//            printCurrentThread();
//            return name;
//        }).map(name -> {
//            System.out.println("entering second map");
//            printCurrentThread();
//            return name;
//        }).subscribeOn(Schedulers.boundedElastic());
//
//        Util.sleepSeconds(5);
//    }

    public static void printThreadName(String msg){
        System.out.println(msg + " : Thread : " + Thread.currentThread().getName());

    }

    public static void main(String[] args) {
        printThreadName("create");
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            for(int i = 0; i < 1; i++){
                fluxSink.next(i);
                Util.sleepSeconds(1);
            }
            fluxSink.complete();
        }).doOnNext(objEmitted -> printThreadName("next " + objEmitted))
                .map(item -> {
                    printThreadName("inside map 1");
                    return item;
                }).map(item -> {
                    printThreadName("inside map 2");
                    return item;
                });

        flux
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(objEmitted -> printThreadName("sub " + objEmitted));

        // required to wait for processes in the prev non-blocking code to complete
        Util.sleepSeconds(5);
    }
}
