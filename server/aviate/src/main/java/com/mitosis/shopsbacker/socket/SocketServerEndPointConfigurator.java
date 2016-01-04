package com.mitosis.shopsbacker.socket;

import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
public class SocketServerEndPointConfigurator extends Configurator {

	private SocketServer chatServer = new SocketServer();

	@SuppressWarnings("unchecked")
	public <T> T getEndpointInstance(Class<T> endpointClass)
			throws InstantiationException {
		return (T)chatServer;
	}
}
