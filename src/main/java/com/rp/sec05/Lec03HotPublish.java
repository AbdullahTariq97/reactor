package com.rp.sec05;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec03HotPublish {
    public static void main(String[] args) {
        // this acts like both a cold and hot publisher
        // it acts cold (one stream for each subscriber) when the stream subscribed by sam has finished streaming and then mike joins
        // in that case, the first stream will have finished streaming, and a new stream will be started for mike
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish() // creates a flux that multiple subscribers can connect to. share() = .publish() + refCount()
                .refCount(1); // this outlines the min number of subscribers that must subscribe to make source emit data

        // person 1
        movieStream.subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(10);

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
