package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Location;
import entity.Settings;
import entity.Stacks;
import entity.User;
import util.JDBC;

public class LocationDao {
	
	public List<Location> findLocationByUserId(User user,Connection conn) {
		List<Location> locationList = new ArrayList<>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("locationId,name,userId,n,e ");
			sql.append("FROM ");
			sql.append("locations ");
			sql.append("WHERE ");
			sql.append("userId = ?; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, user.getUserId());
			rs = stmt.executeQuery();
			//???
			while(rs.next()) {
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setName(rs.getString("name"));
				location.setUserId(rs.getInt("userId"));
				location.setN(rs.getDouble("n"));
				location.setE(rs.getDouble("e"));
				locationList.add(location);
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
		return locationList;
	}
	
	public Location createLocation(Location location,Connection conn) {
		
//		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
//			conn = JDBC.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO locations ");
			sql.append("( ");
			sql.append("name, ");
			sql.append("userId, ");
			sql.append("n, ");
			sql.append("e ");
			sql.append(") ");
			sql.append("VALUES ");
			sql.append("( ");
			sql.append("?, ");
			sql.append("?, ");
			sql.append("?, ");
			sql.append("? ");
			sql.append("); ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, location.getName());
			stmt.setInt(2, location.getUserId());
			stmt.setDouble(3, location.getN());
			stmt.setDouble(4, location.getE());
			stmt.executeUpdate();
			
			sql = new StringBuilder();
			sql.append("SELECT LAST_INSERT_ID() AS id");
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			if(rs.next()) {
//				System.out.println(rs.getInt("id"));
				location.setLocationId(rs.getInt("id"));
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
		return location;
	}
	
//	public void findLocationByDefaultLocation(Settings settings,Connection conn) {
//		
//	}

}
