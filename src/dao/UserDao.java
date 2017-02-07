package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import util.JDBC;

public class UserDao {
	
	public boolean isUserNameExists(User user,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("userEmail ");
			sql.append("FROM ");
			sql.append("users ");
			sql.append("WHERE ");
			sql.append("userName = ?; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, user.getUserName());
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
			return false;  //改为true?
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
	}
	
	public boolean isUserEmailExists(User user,Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("userEmail ");
			sql.append("FROM ");
			sql.append("users ");
			sql.append("WHERE ");
			sql.append("userEmail = ?; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, user.getUserEmail());
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
			return false;  //改为true?
		}
		finally {
			JDBC.setSRnull(stmt, rs);
		}
	}
	
	public User createUser(User user,Connection conn) {
		
//		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
//			conn = JDBC.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ");
			sql.append("( ");
			sql.append("userName, ");
			sql.append("userEmail, ");
			sql.append("userPasswordMd5, ");
			sql.append("userNickname ");
			sql.append(") ");
			sql.append("VALUES ");
			sql.append("( ");
			sql.append("?, ");
			sql.append("?, ");
			sql.append("?, ");
			sql.append("? ");
			sql.append("); ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getUserEmail());
			stmt.setString(3, user.getUserPasswordMd5());
			stmt.setString(4, user.getUserNickname());
			stmt.executeUpdate();
			
			sql = new StringBuilder();
			sql.append("SELECT LAST_INSERT_ID() AS id");
			stmt = conn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user.setUserId(rs.getInt("id"));
//				System.out.println(user.getUserId());
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
		return user;
	}
	
	public User findUserByNameAndPassword(User user) {
		return findUserByModeAndPassword(user,0);
	}
	
	public User findUserByEmailAndPassword(User user) {
		return findUserByModeAndPassword(user,1);
	}
	
	private User findUserByModeAndPassword(User user,int mode) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBC.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT userNickname,userId ");
			sql.append("FROM users ");
			sql.append("WHERE ");
			if(mode==1) {
				sql.append("userEmail =? ");
			}
			else if(mode==0){
				sql.append("userName =? ");
			}
			sql.append("AND userPasswordMd5 =? ;");
//			System.out.println(sql.toString());
			
			stmt = conn.prepareStatement(sql.toString());
			
			if(mode==1) {
				stmt.setString(1, user.getUserEmail());
			}
			else if(mode==0){
				stmt.setString(1, user.getUserName());
			}
			stmt.setString(2, user.getUserPasswordMd5());
			
			rs = stmt.executeQuery();

			if(rs.next()) {
				user.setUserNickname(rs.getString("userNickname"));
				user.setUserId(rs.getInt("userId"));
			}
			else{
				return null;
				//response.sendRedirect("/login");  //用户名或者密码不通过
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
		return user;
	}

}
