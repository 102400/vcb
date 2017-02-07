package show;

import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.Location;
import entity.Stacks;
/**
 * 
 */
public class MyStacksShow {
	
	private int itemListSize;
	private List<Stacks> itemList = new ArrayList<>();
	private List<Book> bookList = new ArrayList<>();
	private List<Location> locationList = new ArrayList<>();

	public int getItemListSize() {
		return itemListSize = itemList.size();
	}

	public List<Stacks> getItemList() {
		return itemList;
	}

	public void setItemList(List<Stacks> itemList) {
		this.itemList = itemList;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}
	

}
