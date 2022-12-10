package com.rp.sec05;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec01ColdPublisher {
    public static void main(String[] args) {
        // cold publishers are publishers that create separate streams for each subscriber as and when the request comes in from them
        // two requests get received by the getMovie method
        // its live netflix being watched by 2 different users at the same time
        // one would be watching movie from the start, whereas the other may be watching movie from the middle
        // two different streams of data will be created for them

        Flux<String> movieStream = Flux.fromStream(() -> getMovie()).delayElements(Duration.ofSeconds(2));

        // person 1
        movieStream.subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(5);

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
