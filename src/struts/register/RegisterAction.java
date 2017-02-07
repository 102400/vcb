package struts.register;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Location;
import entity.User;
import rule.Email;
import rule.Nickname;
import rule.Password;
import rule.Username;
import service.RegisterService;
import util.MD5;

public class RegisterAction extends ActionSupport {
	
	private String nickname;
	private String username;
	private String email;
	private String password;
	private String locationName;
	private double n;  //纬度
	private double e;  //经度
	private String captcha;
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getN() {
		return n;
	}
	public void setN(double n) {
		this.n = n;
	}
	public double getE() {
		return e;
	}
	public void setE(double e) {
		this.e = e;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(nickname==null) {  //什么都没填
			return reload();
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		final String CAPTCHA = (String)session.getAttribute("CAPTCHA");
//		System.out.println(CAPTCHA + ":" + captcha);
		
		if(CAPTCHA==null||!CAPTCHA.equals(captcha)) {
			return reload();
		}
		System.out.println("a");
		if(n>90||n<-90||e>180||e<-180) {  //经纬度合法检测
			return reload();
		}
		System.out.println("b");
		if("".equals(locationName)) {
			return reload();
		}
		System.out.println("c");
		if(!(new Email(email).isLegal()
				&&new Nickname(nickname).isLegal()
				&&new Password(password).isLegal()
				&&new Username(username).isLegal())) {
			return reload();
		}
		System.out.println("d");
		
		System.out.println(this);
		
		User user = new User();
		user.setUserNickname(nickname);
		user.setUserName(username);
		user.setUserEmail(email);
		user.setUserPasswordMd5(MD5.code(password));
		
		Location location = new Location();
		location.setName(locationName);
		location.setN(n);
		location.setE(e);
		
		RegisterService registerService = new RegisterService();
		boolean isSuccess = registerService.tryToRegister(user, location);
		if(isSuccess) {
			ActionContext.getContext().put("message", "注册成功,请登录");
			return "registerSuccess";
		}
		
		return reload();
	}
	
	private String reload() {
		return "reload";
	}
	
	@Override
	public String toString() {
		return "RegisterAction [nickname=" + nickname + ", username=" + username + ", email=" + email + ", password="
				+ password + ", n=" + n + ", e=" + e + ", captcha=" + captcha + "]";
	}
	
	
	
}
