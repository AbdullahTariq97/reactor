package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec07MonoFromFuture {
    public static void main(String[] args) {

        Mono.fromFuture(getName()).subscribe(Util.onNext());

        // What does it mean by blocking a thread
        Util.sleepSeconds(1);

    }

    public static CompletableFuture<String> getName(){
//        CompletableFuture was introduced as part of java 8
        return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
    }

}
