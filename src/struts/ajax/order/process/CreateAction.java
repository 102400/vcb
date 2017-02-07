package struts.ajax.order.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.Stacks;
import entity.User;
import service.OrderService;

public class CreateAction extends ActionSupport {
	
	private int[] itemIds;
	private String result;
	
	
	public int[] getItemIds() {
		return itemIds;
	}
	public void setItemIds(int[] itemIds) {
		this.itemIds = itemIds;
	}
	
	public String getResult() {
		return result;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		if(!(boolean) request.getAttribute("isLogin")) {
			return "404";  //
		}
		int userId = (int) request.getAttribute("userId");
		User user = new User();
		user.setUserId(userId);
		List<Stacks> itemList = new ArrayList<>();
		for(int i:itemIds) {
			Stacks stacks = new Stacks();
			stacks.setItemId(i);
			itemList.add(stacks);
		}
		OrderService orderservice = new OrderService();
		if(orderservice.createOrder(itemList,user)) {
			result = "success";
		}
		else {
			result = "fail";
		}
		return "r";
	}

}
