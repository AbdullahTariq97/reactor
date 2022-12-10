package com.rp.sec06;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec06Parallel {
    public static void main(String[] args) {

        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.parallel()) // the pipeline steps will get shared about the threads from the parallel thread pool
                .doOnNext(i -> printThreadName("next " + i))
                .subscribe(objEmitted -> printThreadName("sub " + objEmitted));

        // required to wait for processes in the prev non-blocking code to complete
        Util.sleepSeconds(5);
    }

    public static void printThreadName(String msg){
        System.out.println(msg + " : Thread : " + Thread.currentThread().getName());
    }
}
