package interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import rule.VerifySource;
import util.MD5;

public class AuthInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String nickname = "";
		int userId = 0;
		String verify = "";
		
		boolean isLogin = false;
		if(request.getCookies()!=null) {
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie:cookies) {
				
				if(cookie.getName().equals("nickname")) {
					String[] sss = cookie.getValue().split("&");  //解密程序
					StringBuilder bs = new StringBuilder();
					for(String str:sss) {
						int x = Integer.valueOf(str);
						char c = (char)x;
						bs.append(c);
					}
					nickname = bs.toString();
				}
				
				if(cookie.getName().equals("user_id")) {
					try {
						userId = Integer.valueOf(cookie.getValue());
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				if(cookie.getName().equals("verify")) {
					verify = cookie.getValue();
				}
			}
		}
		
		String str_verify_source = VerifySource.get(userId + "");    //"#vc" + user_id + "@*!6^xs";
	
		if(verify.equals(MD5.code(str_verify_source))) {
			isLogin = true;
		}
	
		request.setAttribute("isLogin",isLogin);
		request.setAttribute("nickname", nickname);
		request.setAttribute("userId", userId);
		request.setAttribute("verify", verify);
		
		
		//ActionContext.getContext().put("aaa", "!!!");
		//System.out.println("aaa");
		return arg0.invoke();
//		return null;
	}

}
