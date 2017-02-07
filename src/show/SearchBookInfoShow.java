package show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Book;
import entity.Location;
import entity.Stacks;
import entity.User;

public class SearchBookInfoShow {
	
	private List<Stacks> stacksList = new ArrayList<>();
	private List<Book> bookList = new ArrayList<>();
	private List<User> userList = new ArrayList<>();
	private List<Location> locationList = new ArrayList<>();
	
	private Map<Integer,Book> bookIdMap = new HashMap<>();
	private Map<Integer,Location> locationIdMap = new HashMap<>();
	
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
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<Location> getLocationList() {
		return locationList;
	}
	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}
	
	public Map<Integer, Book> getBookIdMap() {
		return bookIdMap;
	}
	public void setBookIdMap(Map<Integer, Book> bookIdMap) {
		this.bookIdMap = bookIdMap;
	}
	public Map<Integer, Location> getLocationIdMap() {
		return locationIdMap;
	}
	public void setLocationIdMap(Map<Integer, Location> locationIdMap) {
		this.locationIdMap = locationIdMap;
	}
	
}
