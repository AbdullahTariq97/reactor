package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleAssignment {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            String countryName = Util.faker().country().name();
            synchronousSink.next(countryName);
        }).map(Object::toString)
                .handle((countryName, synchronousSink) -> {
                    synchronousSink.next(countryName);
            if(countryName.equalsIgnoreCase("canada")) synchronousSink.complete();
                }).subscribe(Util.subscriber());
    }
}
