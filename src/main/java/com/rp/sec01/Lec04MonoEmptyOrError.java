package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyOrError {
    public static void main(String[] args) {


        // you pass in different subscribers that listen to the data emitted by the publisher
        userRepository(1).subscribe(Util.onNext(),
                                            Util.onError(),
                                         Util.onComplete());

        System.out.println("\n");
        userRepository(2).subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());

        System.out.println("\n");
        userRepository(3).subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete());


    }

    // This is acting like a publisher
    public static Mono<String> userRepository(int userId){
        Mono<String> user;

           switch(userId){
            case 1 :
            // .just method wrapper value
            user = Mono.just(Util.faker().name().firstName());
            break;
            case 2 :
            // handles situation when no data is available for user with id 2
            user = Mono.empty();
            break;
           default:
           // handles situation when user id not acceptable. Throws error
            user = Mono.error(new RuntimeException("Not in the allowed range"));
            break;
        };
       return user;
    }

}
