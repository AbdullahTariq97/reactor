package com.rp.sec05;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec04HotPublishAutoConnect {
    public static void main(String[] args) {
        // to stop the hot and cold behaviour for publishers you can use the .autoConnect() method
        // this means if a second subscriber joins after first stream has finished streaming, then a new stream will not be created for it
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .autoConnect(1); // this is the minimum number of subscribers required for publisher to stream
        // if set to zero, it will start to stream before any subscriber subscribes.

        // person 1
        movieStream.subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(10);
        System.out.println("Mike is going to join");

        // person 2
        movieStream.subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(60);
    }

    // netflix
    private static Stream<String> getMovie(){
        System.out.println("got the movie streaming request");
        return Stream.of("scene1",
                "scene2",
                "scene3",
                "scene4",
                "scene5",
                "scene6",
                "scene7");
    }
}
