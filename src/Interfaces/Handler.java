package Interfaces;

import Request.Request;

public interface Handler {

    void setNextHandler(Handler handler); 
    void handleRequest(Request request);
    
}
