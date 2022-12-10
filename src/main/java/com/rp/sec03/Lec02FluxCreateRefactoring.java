package com.rp.sec03;

import com.rp.courseutil.Util;
import com.rp.sec03.helper.NameProducer;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactoring {
    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();

        // programmatically emitting stuff using the flux sink
        Flux.create(nameProducer)
                .subscribe(Util.subscriber());

        nameProducer.produce();

       Runnable runnable = nameProducer::produce;

       // 10 different threads will be trying to invoke the produce method for emitted the data
       for(int i = 0 ; i < 10; i++){
           new Thread(runnable).start();
       }

       Util.sleepSeconds(2);

       // if you want to emit your item via multiple threads, you can make use of FluxSink
    }
}
