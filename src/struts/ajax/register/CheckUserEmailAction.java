package struts.ajax.register;

import java.sql.Connection;

import com.opensymphony.xwork2.ActionSupport;

import dao.UserDao;
import entity.User;
import rule.Email;
import util.JDBC;

public class CheckUserEmailAction extends ActionSupport{
	
	private String result;
	private String email;
	
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getResult() {
		return result;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserEmail(email);
		Connection conn = JDBC.getConnection();
		boolean legal = new Email(email).isLegal();
		boolean exists = new UserDao().isUserEmailExists(user,conn);
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
