package struts.ajax.register;

import java.sql.Connection;

import com.opensymphony.xwork2.ActionSupport;

import dao.UserDao;
import entity.User;
import rule.Email;
import rule.Username;
import util.JDBC;

public class CheckUserNameAction extends ActionSupport {
	
	private String result;
	private String username;

	public void setUsername(String username) {
		this.username = username;
	}

	public String getResult() {
		return result;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserName(username);
		Connection conn = JDBC.getConnection();
		boolean legal = new Username(username).isLegal();
		boolean exists = new UserDao().isUserNameExists(user, conn);
		JDBC.closeConnection(conn);
		if(!legal) {
			result = "illegal";
		}
		else if(exists) {
			result = "exists";
		}
		else {
			result = "ok";
		}
		
		
		return "r";
	}

}
