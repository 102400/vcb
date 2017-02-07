package struts.ajax.order.process;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.Order;
import service.OrderService;

public class ProcessAction extends ActionSupport {
	
	private int status;  //订单状态
	private int orderId;  //查询这个订单是否能被用户操作
	private String u;
	private String result;
	
	
	
	public void setStatus(int status) {
		this.status = status;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getResult() {
		return result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		if(!(boolean) request.getAttribute("isLogin")) {
			result = "fail";
			return "r";
		}
		int userId = (int) request.getAttribute("userId");
		OrderService orderService = new OrderService();
		Order order = new Order();
		order.setOrderId(orderId);
		if(orderService.changeState(order,userId,status,u)!=null) {
			
			status = order.getState();
			result = "success," + status;
			return "r";
		}
		else {
			result = "fail";
			return "r";
		}
	}

}
