package com.mitosis.shopsbacker.socket;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
public class SocketMessageEncoder implements Encoder.Text<SocketMessage> {

    /* (non-Javadoc)
     * @see javax.websocket.Encoder#init(javax.websocket.EndpointConfig)
     */
    public void init(EndpointConfig config) {
        
    }

    /* (non-Javadoc)
     * @see javax.websocket.Encoder#destroy()
     */
    public void destroy() {
    }

    /* (non-Javadoc)
     * @see javax.websocket.Encoder.Text#encode(java.lang.Object)
     */
    public String encode(SocketMessage chatMessage) throws EncodeException {
        return Json.createObjectBuilder()
                        .add("user", chatMessage.getToUser())
                        .add("message", chatMessage.getMessage())
                        .add("salesOrder", chatMessage.getSalesOrder())
                   .build().toString();
    }

}
