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
         switch (chatMessage.getTag().toUpperCase()) {
             case "SALESORDER":
            	 return Json.createObjectBuilder()
                         .add("user", chatMessage.getToUser())
                         .add("message", chatMessage.getMessage())
                         .add("salesOrder", chatMessage.getSalesOrder())
                         .add("tag", chatMessage.getTag())
                         .build().toString();
             case "GEOLOCATION": 
            	 return Json.createObjectBuilder()
                         .add("touser", chatMessage.getToUser())
                         .add("fromuser", chatMessage.getFromUser())
                         .add("tag", chatMessage.getTag())
                         .add("latitude", chatMessage.getLatitude())
                         .add("longitude", chatMessage.getLongitude())
                         .build().toString();
             case "SALESORDERRETURN":
            	 return Json.createObjectBuilder()
                         .add("touser", chatMessage.getToUser())
                         .add("tag", chatMessage.getTag())
                         .add("salesOrderReturn", chatMessage.getSalesOrderReturn())
                         .build().toString();
         }
		return null;
        
    }

}
