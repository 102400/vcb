package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entity.Book;
import entity.Cart;
import entity.Location;
import entity.Stacks;
import entity.User;
import util.JDBC;

public class CartDao {
	
	public boolean isCartExist(User user,Stacks stacks,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT itemId,purchaserId ");
			sql.append("FROM cart ");
			sql.append("WHERE ");
			sql.append("itemId = ?  ");
			sql.append("AND purchaserId = ? ; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, stacks.getItemId());
			stmt.setInt(2, user.getUserId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				return false;
			}
			else {
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return true;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
	}
	
	public boolean addCart(User user,Stacks stacks,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO cart ");
			sql.append("( ");
			sql.append("itemId, ");
			sql.append("purchaserId ");
			sql.append(") ");
			sql.append("VALUES  ");
			sql.append("( ");
			sql.append("?, ");
			sql.append("? ");
			sql.append("); ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, stacks.getItemId());
			stmt.setInt(2, user.getUserId());
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
	
	public boolean deleteCart(User user,Stacks stacks,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM cart ");
			sql.append("WHERE ");
			sql.append("itemId = ? ");
			sql.append("AND purchaserId = ? ; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, stacks.getItemId());
			stmt.setInt(2, user.getUserId());
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
	
	public Set<Integer> findCartItemIdByUserId(User user,Connection conn) {
		Set<Integer> cartItemId = new HashSet<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("itemId ");
			sql.append("FROM cart  ");
			sql.append("WHERE ");
			sql.append("purchaserId = ? ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, user.getUserId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				cartItemId.add(rs.getInt("itemId"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		
		
		return cartItemId;
	}
	
	public List<Object> findCartByUserId(User user,Connection conn) {
		List<Object> list = new ArrayList<>();
		List<Cart> cartList = new ArrayList<>();
//		List<Stacks> itemList = new ArrayList<>();
		List<Book> bookList = new ArrayList<>();
		List<User> ownerList = new ArrayList<>();
		List<Location> ownerlocationList = new ArrayList<>();
//		Cart cart = new Cart();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("c.id AS cartId,c.itemId,c.purchaserId,s.bookId,b.name AS bookName,s.isLoan,s.ownerId,u.userNickname AS ownerNickname,l.locationId,l.name AS locationName,l.n,l.e ");
			sql.append("FROM ");
			sql.append("cart AS c,stacks AS s,books AS b,locations AS l,users AS u ");
			sql.append("WHERE ");
			sql.append("c.purchaserId = ? ");
			sql.append("AND c.itemId = s.itemId ");
			sql.append("AND s.bookId = b.bookId ");
			sql.append("AND s.ownerLocationId = l.locationId ");
			sql.append("AND s.ownerId = u.userId; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, user.getUserId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				if(rs.getBoolean("isLoan")) {
					continue;
				}
				Cart cart = new Cart();
				cart.setId(rs.getInt("cartId"));
				cart.setItemId(rs.getInt("itemId"));
				cart.setPurchaserId(rs.getInt("purchaserId"));
				cartList.add(cart);
				Book book = new Book();
				book.setBookId(rs.getInt("bookId"));
				book.setName(rs.getString("bookName"));
				bookList.add(book);
				User owner = new User();
				owner.setUserId(rs.getInt("ownerId"));
				owner.setUserNickname(rs.getString("ownerNickname"));
				ownerList.add(owner);
				Location ownerlocation = new Location();
				ownerlocation.setLocationId(rs.getInt("locationId"));
				ownerlocation.setName(rs.getString("locationName"));
				ownerlocation.setN(rs.getDouble("n"));
				ownerlocation.setE(rs.getDouble("e"));
				ownerlocationList.add(ownerlocation);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		list.add(cartList);
		list.add(bookList);
		list.add(ownerList);
		list.add(ownerlocationList);
		return list;
	}

}
