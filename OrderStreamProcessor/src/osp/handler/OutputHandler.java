package osp.handler;

import osp.models.TradeRecorder;

public interface OutputHandler {
    void handleTrade(TradeRecorder trade);
}
