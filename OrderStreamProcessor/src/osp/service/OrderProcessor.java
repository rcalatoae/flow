package osp.service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import osp.model.Order;
import osp.model.Trade;
import osp.model.TradeRecorder;

public class OrderProcessor {
    private final HashMap<String, Order> orders;
    private final TradeRecorder tradeRecorder;

    public OrderProcessor(TradeRecorder tradeRecorder) {
        this.orders = new HashMap<>();
        this.tradeRecorder = tradeRecorder;
    }

    public void addOrder(Order order) {
        orders.put(order.getOrderID(), order);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.printf("Order %s expired\n", order.getOrderID());
                orders.remove(order.getOrderID());
            }
        }, 10000);
    }

    public void fillOrder(String orderID, long timestamp, BigDecimal remainingQuantityToFill, BigDecimal price) {
        Order order = orders.get(orderID);
        Trade newTrade = new Trade(timestamp, order.getListing(), order.getSide(), order.getQuantity().subtract(remainingQuantityToFill), price);
        order.setQuantity(remainingQuantityToFill);
        tradeRecorder.recordTrade(newTrade);
        if (remainingQuantityToFill.doubleValue() == 0) {   
            orders.remove(order.getOrderID());
        }
    }

    public void cancelOrder(String orderID) {
        orders.remove(orderID);
    }

    // private void processBuyOrder(Order order) {
    //     while (!sellOrders.isEmpty() && sellOrders.peek().getPrice() <= order.getPrice()) {
    //         Order sellOrder = sellOrders.poll();
    //         double quantity = Math.min(order.getQuantity(), sellOrder.getQuantity());
    //         Trade trade = new Trade(order.getTimestamp(), order.getListing(), order.getSide(), quantity, sellOrder.getPrice());
    //         outputHandler.handleTrade(trade);

    //         if (sellOrder.getQuantity() > quantity) {
    //             sellOrder.setQuantity(sellOrder.getQuantity() - quantity);
    //             sellOrders.add(sellOrder);
    //         }

    //         if (order.getQuantity() > quantity) {
    //             order.setQuantity(order.getQuantity() - quantity);
    //         } else {
    //             break;
    //         }
    //     }

    //     if (order.getQuantity() > 0) {
    //         buyOrders.add(order);
    //     }
    // }

    // private void processSellOrder(Order order) {
    //     while (!buyOrders.isEmpty() && buyOrders.peek().getPrice() >= order.getPrice()) {
    //         Order buyOrder = buyOrders.poll();
    //         double quantity = Math.min(order.getQuantity(), buyOrder.getQuantity());
    //         Trade trade = new Trade(order.getTimestamp(), order.getListing(), order.getSide(), quantity, buyOrder.getPrice());
    //         outputHandler.handleTrade(trade);

    //         if (buyOrder.getQuantity() > quantity) {
    //             buyOrder.setQuantity(buyOrder.getQuantity() - quantity);
    //             buyOrders.add(buyOrder);
    //         }

    //         if (order.getQuantity() > quantity) {
    //             order.setQuantity(order.getQuantity() - quantity);
    //         } else {
    //             break;
    //         }
    //     }

    //     if (order.getQuantity() > 0) {
    //         sellOrders.add(order);
    //     }
    // }
}
