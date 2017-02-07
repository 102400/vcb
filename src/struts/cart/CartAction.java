package struts.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import rule.VerifySource;
import service.CartService;
import show.CartShow;

public class CartAction extends ActionSupport {
	
	CartShow cartshow;
	
	public CartShow getCartshow() {
		return cartshow;
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
		User user = new User();
		user.setUserId(userId);
		CartService cartservice = new CartService();
		cartshow = cartservice.cartShow(user);
		
//		cartshow.getOwnerLocationIdList();  //test
		
		if(cartshow==null) {
			return "404";
		}
		
		cartshow.initOwnerLocationIdMap();
		
		return SUCCESS;
	}

}
