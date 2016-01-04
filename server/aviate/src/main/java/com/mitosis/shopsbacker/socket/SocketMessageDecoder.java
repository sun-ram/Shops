package com.mitosis.shopsbacker.socket;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
public class SocketMessageDecoder implements Decoder.Text<SocketMessage> {

    /* (non-Javadoc)
     * @see javax.websocket.Decoder#init(javax.websocket.EndpointConfig)
     */
    public void init(EndpointConfig config) {
    }

    /* (non-Javadoc)
     * @see javax.websocket.Decoder#destroy()
     */
    public void destroy() {
    }

    /* (non-Javadoc)
     * @see javax.websocket.Decoder.Text#decode(java.lang.String)
     */
    public SocketMessage decode(String s) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
        SocketMessage chatMessage = new SocketMessage();
        chatMessage.setToUser(jsonObject.getString("touser"));
        chatMessage.setMessage(jsonObject.getString("message"));
        return chatMessage;
    }

    /* (non-Javadoc)
     * @see javax.websocket.Decoder.Text#willDecode(java.lang.String)
     */
    public boolean willDecode(String s) {
        return true;
    }
}
