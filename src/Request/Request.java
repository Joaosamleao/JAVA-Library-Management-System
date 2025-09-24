package Request;

import Enums.RequestType;

public class Request {
    
    private RequestType requestType;
    private String description;
    private String id;

    public Request(String id, Enum requestType, String description) {
        this.requestType = (RequestType) requestType;
        this.description = description;
        this.id = id;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }
}
