package entity;

import java.sql.Date;

public class Order {
	
	private int orderId;
	private int state;
	private int ownerId;
	private int purchaserId;
	private int ownerLocationId;
	private Date createTime;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getPurchaserId() {
		return purchaserId;
	}
	public void setPurchaserId(int purchaserId) {
		this.purchaserId = purchaserId;
	}
	public int getOwnerLocationId() {
		return ownerLocationId;
	}
	public void setOwnerLocationId(int ownerLocationId) {
		this.ownerLocationId = ownerLocationId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
