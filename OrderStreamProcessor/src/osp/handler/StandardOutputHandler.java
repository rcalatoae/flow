package osp.handler;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import osp.model.Trade;
import osp.model.TradeRecorder;

public class StandardOutputHandler implements OutputHandler {

    @Override
    public void handleTrade(TradeRecorder tradeRecorder) {
        for (Trade trade: tradeRecorder.getTrades()) {
            System.out.printf("%d\t%s\t%s\t%s\t%s\n", trade.getTimestamp(), trade.getListing(),
                trade.getSide(), trade.getQuantity().toString(), trade.getPrice().toString());
        }
    }
}
