package com.rp.sec06;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04PublisherOn {
    public static void main(String[] args) {
        // the publishOn method allows you to control which thread is used to execute the subscriber part of the pipeline
        // In this example the main thread is used to execute the task to do with the publisher part of the pipline
        // And the subscriber part of the pipeline is created by thread from the elastic thread pool
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            for(int i = 0; i < 4; i++){
                fluxSink.next(i);
            }
            fluxSink.complete();
        }).doOnNext(objEmitted -> printThreadName("next " + objEmitted));

        flux
                .publishOn(Schedulers.boundedElastic())
                .subscribe(objEmitted -> printThreadName("sub " + objEmitted));

        // required to wait for processes in the prev non-blocking code to complete
        Util.sleepSeconds(5);
    }

    public static void printThreadName(String msg){
        System.out.println(msg + " : Thread : " + Thread.currentThread().getName());
    }
}
