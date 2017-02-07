package entity;

public class Cart {
	
	private int id;
	private int itemId;
	private int purchaserId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getPurchaserId() {
		return purchaserId;
	}
	public void setPurchaserId(int purchaserId) {
		this.purchaserId = purchaserId;
	}

}
