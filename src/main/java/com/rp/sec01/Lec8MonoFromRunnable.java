package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec8MonoFromRunnable {

    public static void main(String[] args) {
        // mono from runnable can be used to indicate when a process has finished
        // and if you want to run some business logic once that runnable has co
        //  A runnable accepts no arguments and returns nothing
        // The on com
        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(Util.onNext(),
                        Util.onError(),
                        () -> System.out.println("process is done. sending emails"));
    }

    public static Runnable timeConsumingProcess(){
        return () -> {
            Util.sleepSeconds(3);
            System.out.println("Operation completed");
        };
    }
}
