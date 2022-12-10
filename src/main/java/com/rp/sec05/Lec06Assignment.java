package com.rp.sec05;

import com.rp.courseutil.Util;
import com.rp.sec05.assignment.InventoryService;
import com.rp.sec05.assignment.OrderService;
import com.rp.sec05.assignment.PurchaseOrder;
import com.rp.sec05.assignment.RevenueService;
import reactor.core.publisher.Flux;

public class Lec06Assignment {
    public static void main(String[] args) {
        // can return stream of purchase orders
        OrderService orderService = new OrderService();

        // these can subscribe to the purchase order publisher and in turn also return flux of items
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();

        // hot publisher
        Flux<PurchaseOrder> purchaseOrderFlux = orderService.orderStream();

        purchaseOrderFlux.subscribe(revenueService.subscribeOrderStream());
        revenueService.revenueStream().subscribe(Util.subscriber("revenue"));

        purchaseOrderFlux.subscribe(inventoryService.subscribeOrderStream());
        inventoryService.inventoryStream().subscribe(Util.subscriber("inventory"));

        // The main thread executes lines in this main method line by line
        // The .subscribe method is non blocking, so the main thread does not wait for it to finish before moving to the next line
        // eventually it reaches the end of the line, without waiting for the call stack for the .subscribe method to finish

        // the emission of each object happens after 2 sec, so you have to wait for a least 2 seconds in order for the object to be emitted
        // received and printed by the subscriber. Therefore a wait of 6 seconds will allow 3 object to be received and printed by the subscriber
        Util.sleepSeconds(6);
    }
}
