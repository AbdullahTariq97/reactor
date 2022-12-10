package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromArrayOrList {

    public static void main(String[] args) {
        List<String> strings = List.of("a", "b", "c");

        Flux<String> stringFlux = Flux.fromIterable(strings);
        stringFlux.subscribe(Util.onNext());

        Integer[] arr = {2,5,7,8};

        Flux<Integer> integerFlux = Flux.fromArray(arr);
        integerFlux.subscribe(Util.onNext());
    }
}
