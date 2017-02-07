package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.Cart;
import entity.Location;
import entity.Stacks;
import entity.User;
import util.JDBC;

public class StacksDao {
	
	public List<Object> findStacksByBookId(Book book,Connection conn) {
		
		List<Object> list = new ArrayList<>();
		
		List<Stacks> stacksList = new ArrayList<>();
		List<Book> bookList = new ArrayList<>();
		List<User> userList = new ArrayList<>();
		List<Location> locationList = new ArrayList<>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("s.itemId,s.bookId,b.name AS bookName,s.ownerId,u.userId,u.userNickname,s.isLoan,s.canBorrow,s.ownerLocationId,l.name AS locationName,l.n,l.e ");
			sql.append("FROM ");
			sql.append("stacks AS s,books AS b,users AS u,locations AS l ");
			sql.append("WHERE ");
			sql.append("b.isbn = ? ");
			sql.append("AND s.bookId = b.bookId ");
			sql.append("AND s.ownerId = u.userId ");
			sql.append("AND s.ownerLocationId = l.locationId ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString (1, book.getIsbn());
			rs = stmt.executeQuery();
			while(rs.next()) {
				if(rs.getBoolean("isLoan")||!rs.getBoolean("canBorrow")) {
					continue;
				}
				Stacks stacks = new Stacks();
				Book f_book = new Book();
				User user = new User();
				Location location = new Location();
				
				stacks.setItemId(rs.getInt("itemId"));
				stacks.setBookId(rs.getInt("bookId"));
				f_book.setBookId(rs.getInt("bookId"));
				f_book.setName(rs.getString("bookName"));
				stacks.setOwnerId(rs.getInt("ownerId"));
//				stacks.setHolderId(rs.getInt("holderId"));
//				stacks.setLoan(rs.getBoolean("isLoan"));
//				stacks.setCanBorrow(rs.getBoolean("canBorrow"));
				stacks.setOwnerLocationId(rs.getInt("ownerLocationId"));
				user.setUserId(rs.getInt("userId"));
				user.setUserNickname(rs.getString("userNickname"));
				location.setLocationId(rs.getInt("ownerLocationId"));
				location.setName(rs.getString("locationName"));
				location.setN(rs.getDouble("n"));
				location.setE(rs.getDouble("e"));
				
				stacksList.add(stacks);
				bookList.add(f_book);
				userList.add(user);
				locationList.add(location);
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
		
		return list;
	}
	
	public List<Object> findItemByUserId(User user,Connection conn) {
		List<Object> list = new ArrayList<>();
		List<Stacks> itemList = new ArrayList<>();
		List<Book> bookList = new ArrayList<>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("s.itemId,s.bookId,b.name,s.ownerId,s.holderId,s.isLoan,s.canBorrow,s.ownerLocationId ");
			sql.append("FROM ");
			sql.append("stacks AS s,books AS b ");
			sql.append("WHERE ");
			sql.append("ownerId = ? ");
			sql.append("AND s.bookId = b.bookId; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, user.getUserId());
			rs = stmt.executeQuery();
			//???
			while(rs.next()) {
//				return true;
				Stacks stacks = new Stacks();
				Book book = new Book();
				stacks.setItemId(rs.getInt("itemId"));
				stacks.setBookId(rs.getInt("bookId"));
				book.setBookId(rs.getInt("bookId"));
				book.setName(rs.getString("name"));
				stacks.setOwnerId(rs.getInt("ownerId"));
				stacks.setHolderId(rs.getInt("holderId"));
				stacks.setLoan(rs.getBoolean("isLoan"));
				stacks.setCanBorrow(rs.getBoolean("canBorrow"));
				stacks.setOwnerLocationId(rs.getInt("ownerLocationId"));
				itemList.add(stacks);
				bookList.add(book);
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
		list.add(itemList);
		list.add(bookList);
		return list;
	}
	
	public Stacks findStacksByItemId(Stacks stacks,Connection conn) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("itemId,bookId,ownerId,holderId,isLoan,canBorrow,ownerLocationId ");
			sql.append("FROM ");
			sql.append("stacks ");
			sql.append("WHERE ");
			sql.append("itemId = ? ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, stacks.getItemId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				stacks.setItemId(rs.getInt("itemId"));
				stacks.setBookId(rs.getInt("bookId"));
				stacks.setOwnerId(rs.getInt("ownerId"));
				stacks.setHolderId(rs.getInt("holderId"));
				stacks.setLoan(rs.getBoolean("isLoan"));
				stacks.setCanBorrow(rs.getBoolean("canBorrow"));
				stacks.setOwnerLocationId(rs.getInt("ownerLocationId"));
			}
			else {
//				return null;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		return stacks;
	}
	
	public boolean changeCanBorrow(Stacks stacks,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE stacks ");
			sql.append("SET canBorrow = ? ");
			sql.append("WHERE itemId = ? ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setBoolean(1, stacks.getCanBorrow());
			stmt.setInt(2, stacks.getItemId());
			stmt.executeUpdate();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
	}
	
	public boolean changeISLoanAndholderId(Stacks stacks,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE stacks ");
			sql.append("SET holderId = ?,isLoan = ? ");
			sql.append("WHERE itemId = ? ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, stacks.getHolderId());
			stmt.setBoolean(2, !stacks.getIsLoan());  //?
			stmt.setInt(3, stacks.getItemId());
			stmt.executeUpdate();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
	}
	
	public boolean deleteItem(User user,Book book,Location location,Connection conn) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM stacks ");
			sql.append("WHERE bookId = ? ");
			sql.append("AND ownerId = ? ");
			sql.append("AND ownerLocationId = ?; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, book.getBookId());
			stmt.setInt(2, user.getUserId());
			stmt.setInt(3, location.getLocationId());
			stmt.executeUpdate();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		
	}

	public boolean insertItem(User user,Book book,Location location,Connection conn) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO stacks ");
			sql.append("( ");
			sql.append("bookId, ");
			sql.append("ownerId, ");
			sql.append("holderId, ");
			sql.append("ownerLocationId ");
			sql.append(") ");
			sql.append("VALUES ");
			sql.append("( ");
			sql.append("?, ");
			sql.append("?, ");
			sql.append("?, ");
			sql.append("? ");
			sql.append("); ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, book.getBookId());
			stmt.setInt(2, user.getUserId());
			stmt.setInt(3, user.getUserId());
			stmt.setInt(4, location.getLocationId());
			stmt.executeUpdate();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		
	}
	
	//locationId与bookId为一项
	public boolean isItemExist(Stacks stacks,Connection conn) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
//			conn = JDBC.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("bookId,ownerLocationId ");
			sql.append("FROM ");
			sql.append("stacks ");
			sql.append("WHERE ");
			sql.append("bookId = ? ");
			sql.append("AND ownerLocationId = ?; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, stacks.getBookId());
			stmt.setInt(2, stacks.getOwnerLocationId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
//			JDBC.release(conn, stmt, rs);
			JDBC.setSRnull(stmt, rs);
		}
	}
	
}
