package entity;

public class Stacks {
	
	private int itemId;
	private int bookId;
	private int ownerId;
	private int holderId;
	private boolean isLoan;
	private boolean canBorrow;
	private int ownerLocationId;
	
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getHolderId() {
		return holderId;
	}
	public void setHolderId(int holderId) {
		this.holderId = holderId;
	}
	public boolean getIsLoan() {
		return isLoan;
	}
	public void setLoan(boolean isLoan) {
		this.isLoan = isLoan;
	}
	public boolean getCanBorrow() {
		return canBorrow;
	}
	public void setCanBorrow(boolean canBorrow) {
		this.canBorrow = canBorrow;
	}
	public int getOwnerLocationId() {
		return ownerLocationId;
	}
	public void setOwnerLocationId(int ownerLocationId) {
		this.ownerLocationId = ownerLocationId;
	}

}
