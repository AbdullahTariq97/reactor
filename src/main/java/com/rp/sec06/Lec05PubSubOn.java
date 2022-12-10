package com.rp.sec06;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05PubSubOn {
    public static void main(String[] args) {
        // the task for making the publisher are handled by the threads from the bounded elastic thread pool
        // the task for making the subscriber are handled by the threads from the parallel thread pool
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            for(int i = 0; i < 4; i++){
                fluxSink.next(i);
            }
            fluxSink.complete();
        }).doOnNext(objEmitted -> printThreadName("next " + objEmitted));

        flux
                .publishOn(Schedulers.parallel())
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(objEmitted -> printThreadName("sub " + objEmitted));

        // required to wait for processes in the prev non-blocking code to complete
        Util.sleepSeconds(5);
    }

    public static void printThreadName(String msg){
        System.out.println(msg + " : Thread : " + Thread.currentThread().getName());
    }
}
