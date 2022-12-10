package com.rp.sec03.helper;

import reactor.core.publisher.Flux;

import java.util.*;

public class OrderService {

    private static Map<Integer, List<PurchaseOrder>> db = new HashMap<>();

    static {
        List<PurchaseOrder> list1 = Arrays.asList(
                new PurchaseOrder(1),
                new PurchaseOrder(1),
                new PurchaseOrder(1));

        List<PurchaseOrder> list2 = Arrays.asList(
                new PurchaseOrder(2),
                new PurchaseOrder(2));

        db.put(1, list1);
        db.put(2, list2);
    }

    public static Flux<PurchaseOrder> getOrders(int userId){
        return Flux.create(fluxSink -> {
            List<PurchaseOrder> purchaseOrderList = db.get(userId);
            purchaseOrderList.forEach(fluxSink::next);
            fluxSink.complete();
        });
    }

}
