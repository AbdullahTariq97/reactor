package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Lec06SupplierRefactoring {

    public static void main(String[] args) {

        // We can execute the getName method 3 times.
        // You can see despite the 3 sec delay in the thread the code executes quickly
        // This is because, we only built the pipeline. Since nothing is subscribed to the publisher the pipeline is not executed
        getName();
        // This makes the operation non-blocking. We don't wait for the response to be emitted from supplier on line 26
        // the thread moved to serve next request being sent on line 21
        // Therefore the request on line 15 get served, then we don't wait for line 19, we skip to line 22 and then receive response from publisher when ready
        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.onNext());
        getName();

        Util.sleepSeconds(4);
    }

    public static Mono<String> getName(){
        System.out.println("Entered get name method");
        // by moving the business logic inside the supplier lambda expression it becomes lazily executed
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name..");
            Util.sleepSeconds(3);
            return Util.faker().name().fullName();
        }).map(String::toUpperCase);

    }}