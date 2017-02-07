package struts.order.lend;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import service.OrderBorrowService;
import show.OrderBorrowShow;

public class LendAction extends ActionSupport {
	
	private OrderBorrowShow orderborrowshow;
	
	public OrderBorrowShow getOrderborrowshow() {
		return orderborrowshow;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		if(!(boolean) request.getAttribute("isLogin")) {
			return "needToLogin";
		}
		int userId = (int) request.getAttribute("userId");
		User user = new User();
		user.setUserId(userId);
		OrderBorrowService orderborrowservice = new OrderBorrowService();
		orderborrowshow = orderborrowservice.orderBorrowShow(user,0);  //0为owner
		if(orderborrowshow==null) {
			return "404";
		}
		
		return SUCCESS;
	}

}
