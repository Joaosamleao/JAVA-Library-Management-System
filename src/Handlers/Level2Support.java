package Handlers;

import Interfaces.Handler;
import Request.Request;

public class Level2Support implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(Request request) {

    }
}
