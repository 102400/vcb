package struts.logout;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Cookie user_id = new Cookie("user_id", "");
		Cookie nickname = new Cookie("nickname", "");
		Cookie verify = new Cookie("verify", "");
		
		user_id.setMaxAge(0);
		nickname.setMaxAge(0);
		verify.setMaxAge(0);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addCookie(user_id);
		response.addCookie(nickname);
		response.addCookie(verify);
		
		
		
		return SUCCESS;
	}

}
