package com.rp.sec02;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static void main(String[] args) {
        // Start from 0 and increments by 1 12 times
        Flux<Integer> flux = Flux.range(0,12);
        flux.subscribe(Util.onNext());

        // using the .log method you can see the different methods being invoked as part of the pipeline
        Flux.range(1,10)
                .log()
                .map( (i) -> Util.faker().name())
                .log()
                .subscribe(Util.onNext());


        /**

         Inside Class | method invoke              | type method invoked from
         Any          | .subscribe(subscriber      | Publisher
         Publisher    | .onSubscribe(subscription) | Subscriber
         Subscriber   | request()                  | Subscription
         Publisher    | .onNext(item)              | Subscriber
         Publisher    | onComplete()               | Subscriber

         (1 - .subscriberMethod invoked from publisher object. A subscriber object is passed in)
         public Class Mono{
         }

         Subscriber subscriberObject = new Subscriber();
         Mono monoObject = new Mono();
         monoObject.subscriber(subscriberObject)

         public Class Mono{

         private Subscriber subscriberObject;
         (2 - inside publisher object, .onSubscribe(subscriptionObject) is invoked from Subscriber object and subscription object passed in)
             public void someMethod{
                 Subscription subscriptionObject = new Subscription();
                 subscriberObject.onSubscribe(subscriptionObject)
             }

         }

         Since java is pass by value. When java object is passed as an argument to a method. Then the parameter of that method references the
         location in memory of that object. Therefore is any transformation are done on that variable, then they will effect the object passed in

         public class Subscriber{

         private Subscription subscriptionObject;
             (3 - inside the Subscriber the .request() method is invoked of Subscription object, to request for resource )
             public void someMethod{
                subscriptionObject.request();
             }
         }

         public Class Mono{

         private Subscriber subscriberObject;
             (4 - inside the Publisher the onNext(bookObject) method is invoked from subscription object and item to emmit is passed in)
             public void someMethod{
                Book bookObject = new Book();
                subscriberObject.onNext(bookObject);
             }

         }

         */
    }
}
