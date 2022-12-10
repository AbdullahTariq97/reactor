package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec03DoCallbacks {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            System.out.println("inside create");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
            System.out.println("--completed");
        })
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doFirst(() -> System.out.println("doFirstc"))
                .doOnNext((o) -> System.out.println("doOnNext : " + o))
                .doOnSubscribe((s) -> System.out.println("doOnSubscribe : " + s))
                .doOnRequest((l) -> System.out.println("doOnRequest : " + l))
                .doOnError((e) -> System.out.println("doOnError" + e.getMessage()))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doFinally((signal -> System.out.println("doFinally : " + signal)))
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard : " + o))
                .subscribe(Util.subscriber());
    }
    // The request sent from the subscriber moves from bottom to top through the callback methods
    // The response sent from the publisher moved from the top to the bottom through the callback method
}
