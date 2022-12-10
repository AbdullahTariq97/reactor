package com.rp.sec05;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec05HotPublishCache {
    public static void main(String[] args) {
        // cache = publish().replay()

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .cache(2); // it remembers internally the items will were emitted during emission on first stream for sam
        // so that when mike subscribes, the whole stream is instantly emitted
        // it accepts a parameter, which is how many items it should cache, starting from the last emitted item

        Util.sleepSeconds(2);

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
