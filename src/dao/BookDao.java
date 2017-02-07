package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Book;
import entity.Location;
import entity.Stacks;
import entity.User;
import show.BookShow;
import util.JDBC;

public class BookDao {
	
//	public List<Book> findBookByStacks(List<Stacks> itemList) {
//		
//		List<Book> bookList = new ArrayList<>();
//		
////		int bookId = book.getBookId();
//		
////		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		try {
////			conn = JDBC.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("SELECT ");
//			sql.append("b.bookId,b.bookInfoId,b.name AS bookName,b.publisherId,b.languageId,b.pages,b.isbn,b.price,b.publicationYear,b.publicationMonth ");
//			sql.append("FROM ");
//			sql.append("books AS b ");
//			sql.append("WHERE ");
//			sql.append("b.bookId = ? ");
//			stmt = conn.prepareStatement(sql.toString());
//			stmt.setInt(1, book.getBookId());
//			rs = stmt.executeQuery();
//			if(rs.next()) {
////				System.out.println(bookId);
//				book.setBookId(bookId);
//				book.setBookInfoId(rs.getInt("bookInfoId"));
//				book.setName(rs.getString("bookName"));
//				book.setPublisherId(rs.getInt("publisherId"));
//				book.setLanguageId(rs.getInt("languageId"));
//				book.setPages(rs.getInt("pages"));
//				book.setIsbn(rs.getString("isbn"));
//				book.setPrice(rs.getDouble("price"));
//				book.setPublicationYear(rs.getInt("publicationYear"));
//				book.setPublicationMonth(rs.getInt("publicationMonth"));
//			}
//			else {
//				return null;
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//		finally {
////			JDBC.release(conn, stmt, rs);
//			JDBC.setSRnull(stmt, rs);
//		}
////		System.out.println(bookshow);
//		return book;
//	}
	
	public Book findBookByBookId(Book book,Connection conn) {
		
//		BookShow bookshow = new BookShow();
		int bookId = book.getBookId();
		
//		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
//			conn = JDBC.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("b.bookId,b.bookInfoId,b.name AS bookName,b.publisherId,b.languageId,b.pages,b.isbn,b.price,b.publicationYear,b.publicationMonth ");
			sql.append("FROM ");
			sql.append("books AS b ");
			sql.append("WHERE ");
			sql.append("b.bookId = ? ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, book.getBookId());
			rs = stmt.executeQuery();
			if(rs.next()) {
//				System.out.println(bookId);
				book.setBookId(bookId);
				book.setBookInfoId(rs.getInt("bookInfoId"));
				book.setName(rs.getString("bookName"));
				book.setPublisherId(rs.getInt("publisherId"));
				book.setLanguageId(rs.getInt("languageId"));
				book.setPages(rs.getInt("pages"));
				book.setIsbn(rs.getString("isbn"));
				book.setPrice(rs.getDouble("price"));
				book.setPublicationYear(rs.getInt("publicationYear"));
				book.setPublicationMonth(rs.getInt("publicationMonth"));
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
//			JDBC.release(conn, stmt, rs);
			JDBC.setSRnull(stmt, rs);
		}
//		System.out.println(bookshow);
		return book;
	}
	
	public List<Book> findBookByBookName(Book book,Connection conn) {
		
		List<Book> bookList = new ArrayList<>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
//			conn = JDBC.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("bookId,bookInfoId,name ");
			sql.append("FROM ");
			sql.append("books ");
			sql.append("WHERE ");
			sql.append("name LIKE ? ; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, "%" + book.getName() + "%");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Book f_book = new Book();
				f_book.setBookId(rs.getInt("bookId"));
				f_book.setBookInfoId(rs.getInt("bookInfoId"));
				f_book.setName(rs.getString("name"));
				bookList.add(f_book);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		return bookList;
	}

	public Book findBookByBookIsbn(Book book,Connection conn) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("bookId,bookInfoId,name,isbn ");
			sql.append("FROM ");
			sql.append("books ");
			sql.append("WHERE ");
			sql.append("isbn =  ? ; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, book.getIsbn());
			rs = stmt.executeQuery();
			if(rs.next()) {
				book.setBookId(rs.getInt("bookId"));
				book.setBookInfoId(rs.getInt("bookInfoId"));
				book.setName(rs.getString("name"));
				book.setIsbn(rs.getString("isbn"));
			}
//			else {
//				return null;  //查询不到会404
//			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		return book;
	}
	
	public List<Object> findBookByBookInfo(Book book,Connection conn) {
		
		List<Object> list = new ArrayList<>();
	
		List<Stacks> stacksList = new ArrayList<>();
		List<Book> bookList = new ArrayList<>();
		List<User> userList = new ArrayList<>();
		List<Location> locationList = new ArrayList<>();
		
		Map<Integer,Book> bookIdMap = new HashMap<>();
		Map<Integer,Location> locationIdMap = new HashMap<>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("b.bookId,b.bookInfoId,b.name AS bookName,b.isbn, ");
			sql.append("s.itemId,s.isLoan,s.canBorrow, ");
			sql.append("u.userId AS ownerId,u.userNickname, ");
			sql.append("l.locationId,l.name AS locationName,l.n,l.e ");
			sql.append("FROM ");
			sql.append("books AS b,stacks AS s,users AS u,locations AS l ");
			sql.append("WHERE ");
			sql.append("bookInfoId = ? ");
			sql.append("AND b.bookId = s.bookId ");
			sql.append("AND s.ownerId = u.userId ");
			sql.append("AND s.ownerLocationId = l.locationId; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, book.getBookInfoId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				if(rs.getBoolean("isLoan")||!rs.getBoolean("canBorrow")) {
					continue;
				}
				int bookId = rs.getInt("bookId");
				int locationId = rs.getInt("locationId");
				
				Stacks stacks = new Stacks();
				stacks.setBookId(rs.getInt("bookId"));
				stacks.setItemId(rs.getInt("itemId"));
				stacks.setOwnerId(rs.getInt("ownerId"));
				stacks.setOwnerLocationId(rs.getInt("locationId"));
				Book f_book = new Book();
				f_book.setBookId(rs.getInt("bookId"));
				f_book.setBookInfoId(rs.getInt("bookInfoId"));
				f_book.setName(rs.getString("bookName"));
				f_book.setIsbn(rs.getString("isbn"));
				User user = new User();
				user.setUserId(rs.getInt("ownerId"));
				user.setUserNickname(rs.getString("userNickname"));
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setName(rs.getString("locationName"));
				location.setN(rs.getDouble("n"));
				location.setE(rs.getDouble("e"));
				
				stacksList.add(stacks);
				bookList.add(f_book);
				userList.add(user);
				locationList.add(location);
				
				if(!bookIdMap.containsKey(bookId)) {
					bookIdMap.put(bookId, f_book);
//					System.out.println("b");
				}
				if(!locationIdMap.containsKey(locationId)) {
					locationIdMap.put(locationId, location);
//					System.out.println("l");
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		list.add(stacksList);
		list.add(bookList);
		list.add(userList);
		list.add(locationList);
		list.add(bookIdMap);
		list.add(locationIdMap);
		return list;
	}

}
