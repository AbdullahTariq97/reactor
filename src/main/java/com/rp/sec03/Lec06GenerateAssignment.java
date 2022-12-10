package com.rp.sec03;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec06GenerateAssignment {
    public static void main(String[] args) {
        // requirements
        // exit you have canada
        // maximum number of countries allowed to emmit is 10
        // during the process if the subscriber cancels you should exit

        Flux.generate(synchronousSink -> {
            String countryName = Util.faker().country().name();
            synchronousSink.next(countryName);
            if(countryName.equalsIgnoreCase("canada")){
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }
}
