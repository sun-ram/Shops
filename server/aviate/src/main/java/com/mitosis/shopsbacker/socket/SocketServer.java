package com.mitosis.shopsbacker.socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;


/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */

@ServerEndpoint(value="/websocket/{username}",
configurator=SocketServerEndPointConfigurator.class,
encoders={SocketMessageEncoder.class},
decoders={SocketMessageDecoder.class})

public class SocketServer {

	final static Logger log = Logger.getLogger(SocketServer.class.getName());

	private static final Set<Session> peers =
			Collections.synchronizedSet(new HashSet<Session>());

	static HashMap<String, String> usersMap = new HashMap<String, String>(); 


	@OnOpen
	public void onOpen(Session peer, @PathParam("username") String userName) {
		peers.add(peer);
		usersMap.put(peer.getId(), userName);
		System.out.println("Connected"+""+peer.getId());
	}

	@OnClose
	public void onClose(Session peer) {
		peers.remove(peer);
		usersMap.remove(peer.getId());
		System.out.println("DisConnected"+""+peer.getId());
	}	

	@OnMessage
	public void message(SocketMessage message, Session client)
			throws IOException, EncodeException {
		getSessionIdOfUser(message);
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		t.printStackTrace();
		log.error(t.getMessage());
	}

	/*Retrieving the session id of users*/
	private void getSessionIdOfUser(SocketMessage message) {  
		if (usersMap.containsValue(message.getToUser())) {  
			for (String key : usersMap.keySet()) {  
				if (usersMap.get(key).equals(message.getToUser())) {
					sendMessage(key,message);
				}  
			}
		}  
	}  

	/*Send Message to the user*/
	private void sendMessage(String sessionId, SocketMessage message) {
		for (Session peer : peers) {  
			try {  
				synchronized (peer) {  
					/*Comparing the session id*/  
					if (peer.getId().equals(sessionId)) {  
						peer.getBasicRemote().sendObject(message);   
					}  
				}  
			} catch (Exception e) {  
				peers.remove(peer);  
				e.printStackTrace();
				log.error(e.getMessage());
				try {  
					peer.close();  
				} catch (IOException e1){  
					e1.printStackTrace();
					log.error(e1.getMessage());
				}
				/*for (Session peer : peers) {
					peer.getBasicRemote().sendObject(message);
				}*/
			}
		}
	}  

}
