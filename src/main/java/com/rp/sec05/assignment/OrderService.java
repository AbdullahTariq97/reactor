package com.rp.sec05.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;

public class OrderService {

    private Flux<PurchaseOrder> flux;

    public Flux<PurchaseOrder> orderStream(){
        // we check if the value for flux is null. The first time, it will be null and value for flux initialised
        // the second time, the value will not be null, therefore the existing value will be returned
        if(Objects.isNull(flux)){
            flux = getOrderStream();
        }
        return flux;
    }

    // if we make this public then we will be building the pipeline each time the method is called
    private Flux<PurchaseOrder> getOrderStream(){
        return Flux.interval(Duration.ofMillis(100))
                .map((signal) -> new PurchaseOrder())
                .publish() // hot stream
                .refCount();
    }
}
