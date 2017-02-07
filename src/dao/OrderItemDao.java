package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.Location;
import entity.Order;
import entity.OrderItem;
import entity.Stacks;
import entity.User;
import show.OrderVat;
import util.JDBC;

public class OrderItemDao {
	
	public boolean insertOrderItem(Order order,Stacks stacks,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO orderitems ");
			sql.append("( ");
			sql.append("orderId, ");
			sql.append("itemId ");
			sql.append(") ");
			sql.append("VALUES ");
			sql.append("( ");
			sql.append("?, ");
			sql.append("? ");
			sql.append(");");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, order.getOrderId());
			stmt.setInt(2, stacks.getItemId());
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		return true;
	}
	
	public List<Stacks> findOrderItemByOrderId(Order order,Connection conn) {
		List<Stacks> stacksList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("itemId ");
			sql.append("FROM ");
			sql.append("orderitems ");
			sql.append("WHERE ");
			sql.append("orderId = ?; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, order.getOrderId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Stacks stacks = new Stacks();
				stacks.setItemId(rs.getInt("itemId"));
				stacksList.add(stacks);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		return stacksList;
	}
	
	public List<Object> findOrderItemByOrder(Order order,Connection conn) {
		
		List<Object> list = new ArrayList<>();
		
		List<OrderItem> orderItemList = new ArrayList<>();
		List<Stacks> stacksList = new ArrayList<>();
		List<Book> bookList = new ArrayList<>();
		List<User> ownerList = new ArrayList<>();
		List<User> holderList = new ArrayList<>();
		List<Location> ownerLocationList = new ArrayList<>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("oi.id AS orderitemId,oi.orderId,oi.itemId, ");
			sql.append("b.bookId,b.name AS bookName, ");
			sql.append("ou.userId AS ownerId,ou.userNickname AS ownerNickname, ");
			sql.append("hu.userId AS holderId,hu.userNickname AS holderNickname, ");
			sql.append("s.ownerLocationId, ");
			sql.append("l.name AS locationName,l.n,l.e ");
			sql.append("FROM ");
			sql.append("orderitems AS oi,stacks AS s,books AS b,users AS ou,users AS hu,locations AS l ");
			sql.append("WHERE ");
			sql.append("oi.orderId = ? ");
			sql.append("AND oi.itemId = s.itemId ");
			sql.append("AND s.bookId = b.bookId ");
			sql.append("AND s.ownerId = ou.userId ");
			sql.append("AND s.holderId = hu.userId ");
			sql.append("AND s.ownerLocationId = l.locationId; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, order.getOrderId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				OrderItem orderitem = new OrderItem();
				orderitem.setId(rs.getInt("orderitemId"));
				orderitem.setOrderId(rs.getInt("orderId"));
				orderitem.setItemId(rs.getInt("itemId"));
				Stacks stacks = new Stacks();
				stacks.setItemId(rs.getInt("itemId"));
				stacks.setBookId(rs.getInt("bookId"));
				stacks.setOwnerId(rs.getInt("ownerId"));
				stacks.setHolderId(rs.getInt("holderId"));
				stacks.setOwnerLocationId(rs.getInt("ownerLocationId"));
				Book book = new Book();
				book.setBookId(rs.getInt("bookId"));
				book.setName(rs.getString("bookName"));
				User owner = new User();
				owner.setUserId(rs.getInt("ownerId"));
				owner.setUserNickname(rs.getString("ownerNickname"));
				User holder = new User();
				holder.setUserId(rs.getInt("holderId"));
				holder.setUserNickname(rs.getString("holderNickname"));
				Location ownerlocation = new Location();
				ownerlocation.setLocationId(rs.getInt("ownerLocationId"));
				ownerlocation.setName(rs.getString("locationName"));
				ownerlocation.setN(rs.getDouble("n"));
				ownerlocation.setE(rs.getDouble("e"));
				
				orderItemList.add(orderitem);
				stacksList.add(stacks);
				bookList.add(book);
				ownerList.add(owner);
				holderList.add(holder);
				ownerLocationList.add(ownerlocation);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		list.add(orderItemList);
		list.add(stacksList);
		list.add(bookList);
		list.add(ownerList);
		list.add(holderList);
		list.add(ownerLocationList);
		return list;
		
	}

}
