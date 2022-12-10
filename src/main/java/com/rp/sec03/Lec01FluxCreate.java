package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    public static void main(String[] args) {
        // the publisher create method accepts a consumer which accepts the subscriber that was passed to the publisher
        // this allows you to programtically control when a item gets emitted from a flux
        Flux.create((fluxSink ->
        {
            String countryName =  Util.faker().country().name();

            while(!countryName.equalsIgnoreCase("canada")) {
                fluxSink.next(countryName);
                countryName = Util.faker().country().name();
            }
            fluxSink.next(countryName);
            fluxSink.complete();

        })).subscribe(Util.subscriber());

    }
}
