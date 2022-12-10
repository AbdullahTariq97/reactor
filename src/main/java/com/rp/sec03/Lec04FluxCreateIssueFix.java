package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateIssueFix {
    public static void main(String[] args) {

        Flux.create((fluxSink ->
        {

            String countryName;
            do{
                countryName = Util.faker().country().name();
                System.out.println("emitting: " + countryName);
                fluxSink.next(countryName);
            } while (!countryName.equalsIgnoreCase("canada") && !fluxSink.isCancelled()); // we can check if the subscriber can cancelled before emitting more objects from publisher

            fluxSink.complete();

        }))
                .take(3) // now if we apply the take 3 method to only request for 3 items. The subscriber only receives 3 items, but our source kept on emitting
                .subscribe(Util.subscriber());

        /**
         emitting: Czechia
         received: Czechia
         emitting: Ghana
         received: Ghana
         emitting: Cuba
         received: Cuba
         Completed
         emitting: C√¥te d'Ivoire // it is an issue that the publisher keeps doing unnecessary work
         emitting: Saint Lucia
         emitting: India
         emitting: Philippines
         emitting: Mozambique
         emitting: Saudi Arabia
         emitting: Cyprus
         */
    }
}
