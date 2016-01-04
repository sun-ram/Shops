package com.mitosis.shopsbacker.socket;

/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
public class SocketMessage {
	
	private String fromUser;
	private String message;
	private String  toUser;
	private String salesOrder;
	
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(String salesOrder) {
		this.salesOrder = salesOrder;
	}
	
	}
