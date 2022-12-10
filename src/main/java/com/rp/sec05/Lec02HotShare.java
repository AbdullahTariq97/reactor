package com.rp.sec05;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec02HotShare {
    public static void main(String[] args) {
        // to convert a cold publisher into a hot publisher you can use .share() method
        // only one request is made to the getMovie method for a stream
        // its like a live show is being played. people watching must tune in at the time the show starts otherwise they lose information
        // since mike joined 5 secs after sam, he was only able to watch from scene 3 onwards

        Flux<String> movieStream = Flux.fromStream(() -> getMovie()).delayElements(Duration.ofSeconds(2)).share();

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
