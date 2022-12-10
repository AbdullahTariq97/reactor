package com.rp.sec01;

import java.util.stream.Stream;

public class Lec01Stream {

    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1).map((i) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("running functional interface inside stream ");
            return i*2;
        });
//        System.out.println(stream);
//        The stream is lazy, it does not do anything unless you connect terminal operation
//        stream.forEach(System.out::println);
    }
}
