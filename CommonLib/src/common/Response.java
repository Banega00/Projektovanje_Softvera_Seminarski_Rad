/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author Bane
 */
public class Response {
    private ResponseType responseType;
    private Object data;
    private Exception error;

    public Response(ResponseType responseType, Object data, Exception error) {
        this.responseType = responseType;
        this.data = data;
        this.error = error;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }
    
    
}
