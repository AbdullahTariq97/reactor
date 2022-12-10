package com.rp.sec06;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOnDemo {
    public static void main(String[] args) {
        // for reactor to be non blocking you have the concept of schedulers
        // when the main thread reaches the code creating the pipeline (creating the subscriber, creating the publisher, passing subscriber to publisher, requesting data, passing data...)
        // then a scheduler thread is created by reactor to handle job of creating the pipeline including any I/O calls
        // the main thread in the meantime can move on
        // the person who exposes the publisher has the responsibility to set up the scheduler
        // one single scheduler thread will be responsible for creating the pipeline
        // each subscriber will be assigned a different scheduler from the thread pool

        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        }).doOnNext(objEmitted -> printThreadName("next " + objEmitted));

        flux
                .doFirst(() -> printThreadName("first2"))
                .subscribeOn(Schedulers.boundedElastic()) // schedulers provides factory method for Schedule
                .doFirst(() -> printThreadName("first1"))
                .subscribe(objEmitted -> printThreadName("sub " + objEmitted));

        // required to wait for processes in the prev non-blocking code to complete
        Util.sleepSeconds(5);
    }

    public static void printThreadName(String msg){
        System.out.println(msg + " : Thread : " + Thread.currentThread().getName());

    }
}
