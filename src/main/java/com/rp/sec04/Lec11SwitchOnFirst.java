package com.rp.sec04;

import com.rp.courseutil.Util;
import com.rp.sec03.helper.Person;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.function.Function;

public class Lec11SwitchOnFirst {
    public static void main(String[] args) {
        // switch on first sits somewhere in the pipeline
        // it checks the first item emitted from the publisher for a series checks
        // for example it may be checking the item/data emitted to be in a particular format
        // it does the check for the first item, if checks pass, it allows all items to proceed through main pipeline
        // if the checks on first item does not pass, it redirects the data through another branch
        // this branch transforms the item into correct format
        // this data merges back into the main pipeline

        getPerson()
                .switchOnFirst(((signal, personFlux) -> {
                    System.out.println("inside switch-on-first");
                    return signal.isOnNext() && Objects.requireNonNull(signal.get()).getAge() > 10 ? personFlux : applyFilterMap().apply(personFlux);
                }))
                .subscribe(Util.subscriber());
    }

    public static Flux<Person> getPerson(){
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap(){
        return personFlux -> personFlux
                .filter(person -> person.getAge() > 10)
                .doOnNext(p -> p.setName(p.getName().toUpperCase()))
                .doOnDiscard(Person.class, (person) -> System.out.println("Not allowing : " + person));
    }
}
