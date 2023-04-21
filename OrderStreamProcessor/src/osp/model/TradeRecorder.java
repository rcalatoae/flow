package osp.model;
import java.util.ArrayList;
import java.util.List;

import osp.volumeCalculator.VolumeCalculatorStrategy;

public class TradeRecorder {
    private List<Trade> trades;

    public TradeRecorder() {
        trades = new ArrayList<>();
    }

    public void recordTrade(Trade trade) {
        trades.add(trade);
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void getVolume(VolumeCalculatorStrategy volumeCalculatorMethod) {
        volumeCalculatorMethod.calculateVolume(trades);
    }
}
