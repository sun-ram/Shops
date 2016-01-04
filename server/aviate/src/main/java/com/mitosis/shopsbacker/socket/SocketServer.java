package com.mitosis.shopsbacker.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
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
		System.out.println("Session Opened Client Id "+peer.getId()+" Connected"+userName);
	}

	@OnClose
	public void onClose(Session peer) {
		try{
			peers.remove(peer);
			usersMap.remove(peer.getId());
			System.out.println("Session Closed Client Id "+peer.getId()+" Disconnected");

		}catch(Exception e){
			try {
				peer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		}	

	@OnMessage
	public void message(SocketMessage message, Session client)
			throws IOException, EncodeException {
		System.out.println("Message Received from " + message);
		//	System.out.println(client.getId());
		getSessionIdOfUser(message);
		
	}

	//retrieving the session id of users  
	private void getSessionIdOfUser(SocketMessage message) {  
		if (usersMap.containsValue(message.getToUser())) {  
			for (String key : usersMap.keySet()) {  
				if (usersMap.get(key).equals(message.getToUser())) {
					sendMessage(key,message);
				}  
			}
		}  
	}  
	
	private void sendMessage(String sessionId, SocketMessage message) {
				for (Session peer : peers) {  
					try {  
						synchronized (peer) {  
							//comparing the session id  
							if (peer.getId().equals(sessionId)) {  
								peer.getBasicRemote().sendObject(message); //send message to the user  
							}  
						}  
					} catch (Exception e) {  
						peers.remove(peer);  
						e.printStackTrace();
						try {  
							peer.close();  
						} catch (IOException e1) {  
							e1.printStackTrace();
						}
						/*for (Session peer : peers) {
					peer.getBasicRemote().sendObject(message);
				}*/
					}
				}
	}  

}
