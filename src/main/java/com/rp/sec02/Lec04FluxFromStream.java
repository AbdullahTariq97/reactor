package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {

    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5);

//      Example below will throw Error
        Stream<Integer> stream = list.stream();

        // A stream cannot be operated on more than once by a terminal operator
//        stream.forEach(System.out::println); // closed
//        // Once a stream has been operated on, it is closed. This will throw an error
//        stream.forEach(System.out::println);

        Flux<Integer> flux = Flux.fromStream(stream);

//        flux.subscribe(Util.onNext(),
//                Util.onError(),
//                Util.onComplete());
//      Similarly, this will also throw an error because the same stream is being accessed to emit a flux
//        flux.subscribe(Util.onNext(),
//                Util.onError(),
//                Util.onComplete());

        // The better way is to have the operation of create the stream inside a supplier. This way a new stream is created for each
        // request by a subscriber

        // This fromStream method is overloaded. This one accepts a  supplier
        // Example below will throw Error
        Flux<Integer> betterFlux  = Flux.fromStream(list::stream);

                betterFlux.subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());

                betterFlux.subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());

    }

}
