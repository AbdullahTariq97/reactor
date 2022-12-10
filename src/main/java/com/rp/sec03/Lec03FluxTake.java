package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec03FluxTake {
    public static void main(String[] args) {
        // take is an operator that will allow to emit n items

        Flux.range(1, 10)
                .log()
                .take(3) // after 3rd item the .cancel method is called on the subscription object by this subscriber and onComplete method is called
                .log()
                .subscribe(Util.subscriber());

    }
}
