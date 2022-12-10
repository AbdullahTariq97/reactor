package com.rp.sec04;

import com.rp.courseutil.Util;
import com.rp.sec03.helper.OrderService;
import com.rp.sec03.helper.UserService;
import reactor.core.publisher.Flux;

public class Lec12FlatMap {
    public static void main(String[] args) {
        // the flat maps extracts the actual data from the flux being returned from the consumer inside it and gives it to the subscriber
        // the map returns a flux containing the data, provided the lambda expresion inside returns an object of type flux
        UserService.getUsers()
                .flatMap(user -> OrderService.getOrders(user.getUserId())) // this return flux<object>
                .subscribe(Util.subscriber());
    }
}
