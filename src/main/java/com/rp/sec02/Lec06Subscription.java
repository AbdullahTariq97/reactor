package com.rp.sec02;

import com.rp.courseutil.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class Lec06Subscription {

    public static void main(String[] args) {
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();


        // previously were passing an lambda expression of type consumer to the method which made a subscriber object for us that would get passed to the publisher
        // here we are passing our own implementation of the subscriber object to the publisher
        Flux.range(1,20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("Received Subscription: " + subscription);
                atomicReference.set(subscription);

                // we need to request from the subscription object passed to us so that data is passed to us through onNextMethod
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("On next: " + integer);

            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("On error: " + throwable.getMessage());

            }

            @Override
            public void onComplete() {
                System.out.println("On complete");

            }
        });

        Util.sleepSeconds(3);
        atomicReference.get().request(3); // this request was served
        Util.sleepSeconds(5);
        atomicReference.get().cancel();
        Util.sleepSeconds(3);
        atomicReference.get().request(4); // this did not work once we cancelled
    }
}
