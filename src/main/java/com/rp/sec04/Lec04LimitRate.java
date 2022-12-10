package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec04LimitRate {
    public static void main(String[] args) {
        Flux.range(1,1000)
                .log()
                .limitRate(100, 99) // the request of 1000 items gets divided into requests of 100 items. second argument specified the % of items that has to be drained before next request to publisher
                .subscribe(Util.subscriber());

        // We can use a limit rate. It takes the request coming in from the subscriber.
        // divides it into a smaller request
        // the data from the smaller request gets drained by the subscriber
        // one 75% of the data has been drained, then the limit rate requests another chunk of data from the publisher
        // this is drained again and the cycle repeats

        // in real life the processing of the publisher will be happening by a different thread as compared to the subscriber
        // the thread of the publisher does not know the speed of the subscriber thread. therefore regulation is needed

        // the request of 1000 items gets divided into requests of 100 items. second argument specified the % of
        // items that has to be drained before next request to publisher
        // in order for 100% of the items to be drained before next request is made then the second argument to the limit rate need to be 0


    }
}
