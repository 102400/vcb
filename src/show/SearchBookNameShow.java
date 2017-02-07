package show;

import java.util.ArrayList;
import java.util.List;

import entity.Book;

public class SearchBookNameShow {
	
	private List<Book> bookList = new ArrayList<>();

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

}
