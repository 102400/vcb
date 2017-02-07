package show;

import java.util.List;

import entity.Book;
import entity.Location;
import entity.Stacks;
import entity.User;

public class SearchIsbnShow {
	
	private Book book;
	
	private List<Stacks> stacksList;
	private List<Book> bookList;
	private List<User> userList;
	private List<Location> locationList;

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
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
	

}
