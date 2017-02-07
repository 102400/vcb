package struts.stacks;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import rule.VerifySource;
import service.StacksService;
import show.MyStacksShow;
import show.PeopleStacksShow;

public class PeopleStacksAction extends ActionSupport {
	
	MyStacksShow mystacksshow = new MyStacksShow();
	PeopleStacksShow peoplestacksshow = new PeopleStacksShow();

	public MyStacksShow getMystacksshow() {
		return mystacksshow;
	}
	
	public PeopleStacksShow getPeoplestacksshow() {
		return peoplestacksshow;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int r_userId = (int) request.getAttribute("userId");
		
		String namespace = ServletActionContext.getActionMapping().getNamespace();
//		System.out.println(namespace);
		String str = namespace.split("/")[2];
//		System.out.println(str);
		int userId;
		try {
			userId = Integer.valueOf(str);
			if(userId==r_userId&&(boolean)request.getAttribute("isLogin")) {
				return "myStacks";
			}
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			return "404";
		}
			
		StacksService stacksservice = new StacksService();
		User user = new User();
		user.setUserId(userId);
		mystacksshow = stacksservice.myStacksShow(user);
		if(mystacksshow==null) {
			return "404";
		}
		user.setUserId(r_userId);
		peoplestacksshow = stacksservice.peopleStacksShow(user);
		if(peoplestacksshow==null) {
			return "404";
		}
		
		return SUCCESS;
	}

}
