package struts.ajax.stacks.people.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.Book;
import entity.Stacks;
import entity.User;
import rule.VerifySource;
import service.CartService;
import service.StacksService;

public class CartAction extends ActionSupport {
	
	private String status;
	private int itemId;
	private String result;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getResult() {
		return result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		if(!(boolean) request.getAttribute("isLogin")) {
			result = "error";
			return "r";
		}
		int userId = (int) request.getAttribute("userId");
		CartService cartservice = new CartService();
		
//		System.out.println(this);
		
		User user = new User();
		user.setUserId(userId);
		Stacks stacks = new Stacks();
		stacks.setItemId(itemId);
		
		if("out".equals(status)||"in".equals(status)) {
			if("out".equals(status)) {  //加入借阅箱
				if(cartservice.addCart(user, stacks)) {
					result = "success";
				}
				else {
					result = "fail";
				}
			}
			else if("in".equals(status)) {  //从借阅箱中删除
				if(cartservice.deleteCart(user, stacks)) {
					result = "success";
				}
				else {
					result = "fail";
				}
			}
		}
		else {
			result = "fail";
		}
		return "r";
	}
	
//	@Override
//	public String toString() {
//		return "OwnAction [own=" + own + ", bookId=" + bookId + ", userId=" + userId + ", verify=" + verify
//				+ ", result=" + result + "]";
//	}
	
	

}
