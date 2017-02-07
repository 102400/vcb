package struts.people;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PeopleAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String str = ServletActionContext.getActionMapping().getName();
		
		int userId;
		try {
			userId = Integer.valueOf(str);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			return "404";
		}
		ActionContext.getContext().put("peopleId", userId);
		
		return SUCCESS;
	}

}
