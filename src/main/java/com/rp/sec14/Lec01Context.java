package com.rp.sec14;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Context {

    // Execution moves furthest from mono to closest to it. The last modification is then done by closest to it
    // context is sent by the subscriber to the publisher
    // the publisher can manipulate this context and send it back

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(context -> context.put("user", context.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user", "moh")) // context gets sent by subscriber
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage(){ // This acting like a reactive endpoint
        return Mono.deferContextual((context) -> { // based on the context you can decide which type of mono to return back
            if(context.hasKey("user")){
                return Mono.just("Welcome " + context.get("user"));
            } else {
                return Mono.error(new RuntimeException("unauthenticated"));
            }
        });
    }
}
//Context in Em is from ratpack. It represents the request and response
