package show;

import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.Location;
import entity.Order;
import entity.OrderItem;
import entity.Stacks;
import entity.User;

public class OrderVat {
	
	private Order order;
	private List<OrderItem> orderItemList = new ArrayList<>();
	private List<Stacks> stacksList = new ArrayList<>();
	private List<Book> bookList = new ArrayList<>();
	private List<User> ownerList = new ArrayList<>();
	private List<User> holderList = new ArrayList<>();
	private List<Location> ownerLocationList = new ArrayList<>();
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public List<Stacks> getStacksList() {
		return stacksList;
	}
	public void setStacksList(List<Stacks> stacksList) {
		this.stacksList = stacksList;
	}
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	public List<User> getOwnerList() {
		return ownerList;
	}
	public void setOwnerList(List<User> ownerList) {
		this.ownerList = ownerList;
	}
	public List<User> getHolderList() {
		return holderList;
	}
	public void setHolderList(List<User> holderList) {
		this.holderList = holderList;
	}
	public List<Location> getOwnerLocationList() {
		return ownerLocationList;
	}
	public void setOwnerLocationList(List<Location> ownerLocationList) {
		this.ownerLocationList = ownerLocationList;
	}
	
	

}
