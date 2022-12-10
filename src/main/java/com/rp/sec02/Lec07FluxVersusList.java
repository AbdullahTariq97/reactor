package com.rp.sec02;

import com.rp.courseutil.Util;
import com.rp.sec02.helper.NameGenerator;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec07FluxVersusList {

    public static void main(String[] args) {
//        List<String> listOfNames = NameGenerator.getNames(5);
        // we have to wait for 5 secs before the list is given to us
//        System.out.println(listOfNames);

//      You dont have to wait five seconds, you get the value as its emitted
        Flux<String> flux = NameGenerator.getNames(5);
        flux.subscribe(Util.onNext()); // this method create a subscriber in the background which gets passed to the flux publisher
    }
}
