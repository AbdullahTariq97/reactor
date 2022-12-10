package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec01FluxIntro {
    public static void main(String[] args) {
        // Flux emits zero or n items, at the end of which onComplete or onError is called
        Flux<Integer> flux = Flux.just(1,2,3,4);

        // The consumer interface implementation will be invoked for each piece of data emitted from flux
        // After all the data has been emitted, the consumer for onComplete is executed
        flux.subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());
    }
}
