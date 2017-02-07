package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Location;
import entity.Settings;
import entity.User;
import util.JDBC;

public class SettingsDao {
	
	public boolean createDefaultLocationId(Location location,Connection conn) {
		
//		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
//			conn = JDBC.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO settings ");
			sql.append("( ");
			sql.append("userId, ");
			sql.append("defaultLocationId ");
			sql.append(") ");
			sql.append("VALUES ");
			sql.append("( ");
			sql.append("?, ");
			sql.append("? ");
			sql.append("); ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, location.getUserId());
			stmt.setInt(2, location.getLocationId());
			stmt.executeUpdate();
			
			sql = new StringBuilder();
			sql.append("SELECT LAST_INSERT_ID() AS id");
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			return true;
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
	
	public Location findDefaultLocationIdByUserId(User user,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT userId,defaultLocationId ");
			sql.append("FROM settings ");
			sql.append("WHERE userId = ? ; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, user.getUserId());
			stmt.execute();
			rs = stmt.executeQuery();
			if(rs.next()) {
				Location location = new Location();
				location.setLocationId(rs.getInt("defaultLocationId"));
				return location;
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

}
