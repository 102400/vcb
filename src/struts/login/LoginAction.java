package struts.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.UserDao;
import entity.User;
import rule.VerifySource;
import util.MD5;

public class LoginAction extends ActionSupport {
	
	private String username;
	private String password;
	private String captcha;
	private String remember;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getRemember() {
		return remember;
	}
	public void setRemember(String remember) {
		this.remember = remember;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(username==null) {
			return reload();
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		final String CAPTCHA = (String)session.getAttribute("CAPTCHA");
		if(CAPTCHA==null||!CAPTCHA.equals(captcha)) {
			return reload();
		}
		boolean isEmail = false;
		for(char c:username.toCharArray()) {
			if(c=='@') {
				isEmail = true;
				break;
			}
		}
		
		UserDao userdao = new UserDao();
		User user = new User();
		user.setUserPasswordMd5(MD5.code(password));
		if(isEmail) {
			user.setUserEmail(username);
			user = userdao.findUserByEmailAndPassword(user);
		}
		else {
			user.setUserName(username);
			user = userdao.findUserByNameAndPassword(user);
		}
		if(user==null) {
			return reload();
		}
		
		String nickname = user.getUserNickname();
		int userId = user.getUserId();
		
		StringBuilder sb = new StringBuilder();  //简单加密过后的nickname
		for(char c:nickname.toCharArray()) {
			sb.append((int)c + "&");
		}
		
//		String str_verify_source = VerifySource.get(user_id + "");  //"#vc" + user_id + "@*!6^xs";
		String str_verify_md5 = VerifySource.getMD5(userId + "");
		
		Cookie cookie_nickname = new Cookie("nickname",sb.toString());
		Cookie cookie_user_id = new Cookie("user_id", userId + "");
		
		Cookie verify = new Cookie("verify",str_verify_md5);  //加密过后的 登录验证cookie
		verify.setHttpOnly(true);
		
		if("on".equals(remember)) {
			verify.setMaxAge(60*60*24*42);  //42天
			cookie_nickname.setMaxAge(60*60*24*42);
			cookie_user_id.setMaxAge(60*60*24*42);
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addCookie(verify);
		response.addCookie(cookie_nickname);
		response.addCookie(cookie_user_id);
		
		return "loginSuccess";
	}
	
	private String reload() {
		return "reload";
	}

}
