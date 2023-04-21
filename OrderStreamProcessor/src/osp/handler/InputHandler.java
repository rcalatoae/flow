package osp.handler;

import osp.service.OrderProcessor;

public interface InputHandler {
    void handleInput(String input, OrderProcessor orderProcessor);
}
