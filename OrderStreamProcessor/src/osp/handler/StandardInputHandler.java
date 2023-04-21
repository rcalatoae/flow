package osp.handler;
import java.math.BigDecimal;

import osp.models.OpCode;
import osp.models.Order;
import osp.models.Side;
import osp.service.OrderProcessor;

public class StandardInputHandler implements InputHandler{

    @Override
    public void handleInput(String input, OrderProcessor orderProcessor) {
        String[] orderStrings = input.split("\n");
        for (String orderString: orderStrings) {
            String[] tokens = orderString.split(" ");
            long timestamp = Long.parseLong(tokens[0]);
            String opcode = tokens[1];
            String data = tokens[2];
            for (int i = 3; i < tokens.length; i++) {
                data += "\t" + tokens[i];
            }
            if (opcode.equals(OpCode.CREATED.toString())) {
                String[] orderData = data.split("\t");
                String orderId = orderData[0];
                BigDecimal price = new BigDecimal(orderData[1]);
                BigDecimal quantity = new BigDecimal(orderData[2]);
                String side = orderData[3];
                String listing = orderData[4];
                Order order = new Order(timestamp, orderId, price, quantity, Side.valueOf(side.trim()), listing);
                orderProcessor.addOrder(order);
            } else if (opcode.equals(OpCode.FILLED.toString())) {
                String[] fillData = data.split("\t");
                String orderId = fillData[0];
                BigDecimal fillPrice = new BigDecimal(fillData[1]);
                BigDecimal remainingQuantity = new BigDecimal(fillData[2]);
                orderProcessor.fillOrder(orderId, timestamp, remainingQuantity, fillPrice);
            } else if (opcode.equals(OpCode.CANCELLED.toString())) {
                String orderId = data;
                orderProcessor.cancelOrder(orderId);
            }
        }
    }
    
}
