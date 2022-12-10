package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxGenerate {

    public static void main(String[] args) {
        // Using FluxSink you can keep on emitting data
        // Using SynchronousSink you can emmit data only once using .next() method

        // with FluxSink you have to maintain loop of requesting more data from publisher and passing more data to the subscriber
        // with SynchronousSink you do not have to maintain the loop. The generate method invokes the lambda expression passed to it again and again

        Flux.generate(synchronousSink ->{
            System.out.println("emitting");
            synchronousSink.next(Util.faker().country().name());
//            synchronousSink.next(Util.faker().country().name());
            synchronousSink.complete();
        } ).take(2).subscribe(Util.subscriber());
    }
}
