package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.BookDao;
import dao.StacksDao;
import entity.Book;
import entity.Location;
import entity.Stacks;
import entity.User;
import show.SearchBookInfoShow;
import show.SearchBookNameShow;
import show.SearchIsbnShow;
import util.JDBC;

public class SearchService {
	
	public SearchBookInfoShow searchBookInfoShow(Book book) {
		
		SearchBookInfoShow searchbookinfoshow = new SearchBookInfoShow();
		
		Connection conn = JDBC.getConnection();
		
		try {
			BookDao bookdao = new BookDao();
			List<Object> list = bookdao.findBookByBookInfo(book, conn);
			if(list==null) {
				return null;
			}
			searchbookinfoshow.setStacksList((List<Stacks>) list.get(0));
			searchbookinfoshow.setBookList((List<Book>) list.get(1));
			searchbookinfoshow.setUserList((List<User>) list.get(2));
			searchbookinfoshow.setLocationList((List<Location>) list.get(3));
			searchbookinfoshow.setBookIdMap((Map<Integer, Book>) list.get(4));
			searchbookinfoshow.setLocationIdMap((Map<Integer, Location>) list.get(5));
		}
		finally {
			JDBC.closeConnection(conn);
		}
		return searchbookinfoshow;
		
	}
	
	public SearchBookNameShow searchBookNameShow(Book book) {
		
		SearchBookNameShow searchbooknameshow = new SearchBookNameShow();
		
		Connection conn = JDBC.getConnection();
		
		try {
			BookDao bookdao = new BookDao();
			List<Book> bookList = bookdao.findBookByBookName(book, conn);
			if(bookList==null) {
				return null;
			}
			searchbooknameshow.setBookList(bookList);
		}
		finally {
			JDBC.closeConnection(conn);
		}
		return searchbooknameshow;
		
	}
	
	public SearchIsbnShow searchIsbnShow(Book book) {
		
		SearchIsbnShow searchisbnshow = new SearchIsbnShow();
		
		Connection conn = JDBC.getConnection();
		
		try {
			BookDao bookdao = new BookDao();
			book = bookdao.findBookByBookIsbn(book, conn);
			if(book==null) {
				return null;
			}
			searchisbnshow.setBook(book);
			
			StacksDao stacksdao = new StacksDao();
			List<Object> list = stacksdao.findStacksByBookId(book, conn);
			if(list==null) {
				return null;
			}
			searchisbnshow.setStacksList((List<Stacks>) list.get(0));
			searchisbnshow.setBookList((List<Book>) list.get(1));
			searchisbnshow.setUserList((List<User>) list.get(2));
			searchisbnshow.setLocationList((List<Location>) list.get(3));
			
		}
		finally {
			JDBC.closeConnection(conn);
		}
		return searchisbnshow;
	}
	
}
