package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.LocationDao;
import dao.SettingsDao;
import dao.UserDao;
import entity.Location;
import entity.User;
import util.JDBC;

public class RegisterService {
	
	public boolean tryToRegister(User user,Location location) {
		
		Connection conn = JDBC.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			UserDao userdao = new UserDao();
			LocationDao locationdao = new LocationDao();
			SettingsDao settingsdao = new SettingsDao();
			
			user = userdao.createUser(user,conn);
			if(user==null) {
				JDBC.rollback(conn);
				return false;
			}
			
			location.setUserId(user.getUserId());
			location = locationdao.createLocation(location,conn);
			if(location==null) {
				JDBC.rollback(conn);
				return false;
			}
			
			if(settingsdao.createDefaultLocationId(location,conn)) {
				JDBC.commit(conn);
				return true;
			}
			else {
				JDBC.rollback(conn);
				return false;
			}
		}
		finally {
			JDBC.closeConnection(conn);
		}
	}

}
