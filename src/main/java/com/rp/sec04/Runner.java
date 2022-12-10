package com.rp.sec04;

import com.rp.courseutil.Util;
import com.rp.sec03.helper.Person;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Runner {
    public static void main(String[] args) {
        // The .map operation does not modify the Flux object passed to it. It returns a new Flux object using the flux object passed to it

        // therefore this would not work
        Person person1 = new Person();
        person1.setName("Abdullah");
        Flux<Person> personFlux1 = Flux.just(person1);
        personFlux1.map(Person::getName);
        personFlux1.subscribe(Util.subscriber());

        // but this would work
        Person person2 = new Person();
        person2.setName("Abdullah");
        Flux<Person> personFlux2 = Flux.just(person2);

        Function<Person, String> stringFunction = (person) -> person.getName();
        Flux<String> personStringFlux = personFlux2.map(stringFunction);
        // here we subscriber to the flux parameterised with string being produced by .map operation
        personStringFlux.subscribe(Util.subscriber());

        /**
         Map converts a single input to a single output

         FlatMap converts a single input into multiple outputs (Publisher<Object>>)
         Each input converts to a stream. A subscriber subscribers to multiple streams.
         It does not wait for a stream to be finished before moving onto the next stream. Therefore order is not maintained
         */

        Function<Person, Flux<Person>> function = (person) -> Flux.just(person);

        Person person3 = new Person();
        person3.setName("Abdullah");
        Flux<Person> flux4 = Flux.just(person3);
        // the flat map method accepts a lambda expression of type function which has return type a publisher like mono or flux Publisher<T>
        // then the return type of the flatmap is a at Flux<T>
        // this is an important difference to the .map operation
        Flux<Person> flux5 = flux4.flatMap(function);
        flux5.subscribe(Util.subscriber());
    }
}
