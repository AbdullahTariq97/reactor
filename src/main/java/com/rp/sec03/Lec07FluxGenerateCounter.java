package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateCounter {
    public static void main(String[] args) {
        // the generate function is overloaded. The first lambda expression in this method get invoked once
        // the second BiFunction lambda expression updates the current state. It gets invoked again and again

        Flux.generate(() -> 1,
                (count,sink) ->{
            String country = Util.faker().country().name();
            sink.next(country);
            if(count >= 10 || country.equalsIgnoreCase("canada")) sink.complete();
            return count + 1;
        })
                .take(4)
                .subscribe(Util.subscriber());
    }
}
