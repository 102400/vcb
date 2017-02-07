package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Order;
import util.JDBC;

public class OrderStateDao {
	
	public boolean insertOrderState(Order order,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO orderstate ");
			sql.append("( ");
			sql.append("orderId, ");
			sql.append("state ");
			sql.append(") ");
			sql.append("VALUES ");
			sql.append("( ");
			sql.append("?, ");
			sql.append("? ");
			sql.append(");");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, order.getOrderId());
			stmt.setInt(2, order.getState());
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

}
