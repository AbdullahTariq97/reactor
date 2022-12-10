package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec05MonoFromSupplier {
    public static void main(String[] args) {

        // use just only if you are sure data is available
        // you can see that the getName method gets executed.
        // we don't want the method to get executed, until there are subscribers subscribed to the mono publisher
//        Mono<String> mono = Mono.just(getName());

        // The statement below will not do anything unless there is a subscriber
        // the fromSupplier interface accepts a type of supplier functional interface
        Mono<String> monoFromSupplier = Mono.fromSupplier(() -> getName());
        monoFromSupplier.subscribe(Util.onNext());

        // the fromSupplier interface accepts a type of callable functional interface
        Mono<String> monoFromCallable = Mono.fromCallable(() -> getName());
        monoFromCallable.subscribe(Util.onNext());
    }

    public static String getName(){
        System.out.println("Get name method gets executed");
        return Util.faker().name().firstName();
    }

}
