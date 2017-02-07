package struts.stacks;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import rule.VerifySource;
import service.StacksService;
import show.MyStacksShow;

public class MyStacksAction extends ActionSupport {
	
	MyStacksShow mystacksshow = new MyStacksShow();

	public MyStacksShow getMystacksshow() {
		return mystacksshow;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int userId = (int) request.getAttribute("userId");
		
		if(!VerifySource.getMD5(userId + "").equals(request.getAttribute("verify"))) {
//			System.out.println("aaa");
			return "verifyFail";
		}
		
		StacksService stacksservice = new StacksService();
		User user = new User();
		user.setUserId(userId);
		mystacksshow = stacksservice.myStacksShow(user);
		if(mystacksshow==null) {
			return "404";
		}
		
		return SUCCESS;
	}

}
