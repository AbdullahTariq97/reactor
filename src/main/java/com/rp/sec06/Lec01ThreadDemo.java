package com.rp.sec06;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec01ThreadDemo {
    public static void main(String[] args) {
//        Flux<Object> flux = Flux.create(fluxSink -> {
//            printThreadName("create");
//            fluxSink.next(1);
//        }).doOnNext(objEmitted -> printThreadName("next " + objEmitted));
//
//        Runnable runnable = () -> flux.subscribe(objEmitted -> printThreadName("sub " + objEmitted));
//
//        // we want to execute the runnable using two different threads
//        for(int i = 0 ; i < 2; i++){
//            new Thread(runnable).start();
//        }
//
//        Util.sleepSeconds(5);
        printThreadName("inside main method");

        Flux.just("1")
                .map(data -> {
                    printThreadName("inside map 1");
                    return data;
                })
                .map(data -> {
                    printThreadName("inside map 2");
                    return data;
                }).flatMap(data -> {
                    printThreadName("inside flatmap");
                    return Flux.just("2");
                })
                .doOnNext(data -> printThreadName("data emitted"))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(data -> printThreadName("receiving data"));

//        Util.sleepSeconds(5);

    }

    public static void printThreadName(String msg){
        System.out.println(msg + " Thread : " + Thread.currentThread().getName());

    }
}
