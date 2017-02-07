package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Order;
import entity.User;
import show.OrderVat;
import util.JDBC;

public class OrderDao {
	
	public Order insertOrder(Order order,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO orders ");
			sql.append("( ");
			sql.append("state, ");
			sql.append("ownerId, ");
			sql.append("purchaserId, ");
			sql.append("ownerLocationId ");
			sql.append(") ");
			sql.append("VALUES ");
			sql.append("( ");
			sql.append("?, ");
			sql.append("?, ");
			sql.append("?, ");
			sql.append("? ");
			sql.append(");");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, order.getState());
			stmt.setInt(2, order.getOwnerId());
			stmt.setInt(3, order.getPurchaserId());
			stmt.setInt(4, order.getOwnerLocationId());
			stmt.executeUpdate();
			sql = new StringBuilder();
			sql.append("SELECT LAST_INSERT_ID() AS id");
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			if(rs.next()) {
				int orderId = rs.getInt("id");
//				System.out.println(orderId);
				order.setOrderId(orderId);
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
			JDBC.setSRnull(stmt, rs);
		}
		return order;
	}

	public List<OrderVat> findOrderByPurchaserId(User user,Connection conn) {
		List<OrderVat> orderList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("orderId,state,ownerId,purchaserId,ownerLocationId,createTime ");
			sql.append("FROM ");
			sql.append("orders ");
			sql.append("WHERE ");
			sql.append("purchaserId = ?; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, user.getUserId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				OrderVat vat = new OrderVat();
				Order order = new Order();
				order.setOrderId(rs.getInt("orderId"));
				order.setState(rs.getInt("state"));
				order.setOwnerId(rs.getInt("ownerId"));
				order.setPurchaserId(rs.getInt("purchaserId"));
				order.setOwnerLocationId(rs.getInt("ownerLocationId"));
				order.setCreateTime(rs.getDate("createTime"));
				vat.setOrder(order);
				orderList.add(vat);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		return orderList;
	}
	
	public List<OrderVat> findOrderByOwnerId(User user,Connection conn) {
		List<OrderVat> orderList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("orderId,state,ownerId,purchaserId,ownerLocationId,createTime ");
			sql.append("FROM ");
			sql.append("orders ");
			sql.append("WHERE ");
			sql.append("ownerId = ?; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, user.getUserId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				OrderVat vat = new OrderVat();
				Order order = new Order();
				order.setOrderId(rs.getInt("orderId"));
				order.setState(rs.getInt("state"));
				order.setOwnerId(rs.getInt("ownerId"));
				order.setPurchaserId(rs.getInt("purchaserId"));
				order.setOwnerLocationId(rs.getInt("ownerLocationId"));
				order.setCreateTime(rs.getDate("createTime"));
				vat.setOrder(order);
				orderList.add(vat);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
		return orderList;
	}
	
	public Order findOrderByOrderIdHolder(Order order,Connection conn) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("orderId,state,ownerId,purchaserId,ownerLocationId ");
			sql.append("FROM ");
			sql.append("orders ");
			sql.append("WHERE ");
			sql.append("orderId = ?; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, order.getOrderId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				order.setOwnerId(rs.getInt("ownerId"));
				order.setPurchaserId(rs.getInt("purchaserId"));
				order.setState(rs.getInt("state"));  //?
				return order;
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
			JDBC.setSRnull(stmt, rs);
		}
	}
	
	public Order findOrderByOrderIdOwner(Order order,Connection conn) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("orderId,state,ownerId,purchaserId,ownerLocationId ");
			sql.append("FROM ");
			sql.append("orders ");
			sql.append("WHERE ");
			sql.append("orderId = ?; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, order.getOrderId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				order.setOwnerId(rs.getInt("ownerId"));
				order.setPurchaserId(rs.getInt("purchaserId"));
				//order.setState(rs.getInt("state"));
				return order;
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
			JDBC.setSRnull(stmt, rs);
		}
	}
	
	public Order changeStateHolder(Order order,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE orders ");
			sql.append("SET state = ? ");
			sql.append("WHERE ");
			sql.append("orderId = ?; ");
			stmt = conn.prepareStatement(sql.toString());
			if(order.getState()==1) {
				order.setState(2);
				stmt.setInt(1, 2);
			}
			else if(order.getState()==2) {
				order.setState(3);
				stmt.setInt(1, 3);
			}
			else if(order.getState()==3) {
				order.setState(4);
				stmt.setInt(1, 4);
			}
			else if(order.getState()==4) {
				order.setState(5);
				stmt.setInt(1, 5);
				
			}
			else if(order.getState()==-1) {
				order.setState(-99);
				stmt.setInt(1, -99);
			}
			stmt.setInt(2, order.getOrderId());
			stmt.executeUpdate();
			
			return order;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
	}
	
}
