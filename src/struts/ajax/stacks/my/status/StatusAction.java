package struts.ajax.stacks.my.status;

import com.opensymphony.xwork2.ActionSupport;

import entity.Book;
import entity.Stacks;
import entity.User;
import rule.VerifySource;
import service.StacksService;

public class StatusAction extends ActionSupport {
	
	private String status;
	private int itemId;
	private int userId;
	private String verify;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	public String getResult() {
		return result;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(!VerifySource.getMD5(userId + "").equals(verify)) {
//			System.out.println("aaa");
			result = "error";
			return "r";
		}
		StacksService stacksservice = new StacksService();
		
//		System.out.println(this);
		
//		User user = new User();
//		user.setUserId(userId);
		Stacks stacks = new Stacks();
		stacks.setItemId(itemId);
		
		if("public".equals(status)||"private".equals(status)) {
			if("public".equals(status)) {
				stacks.setCanBorrow(false);
			}
			else if("private".equals(status)) {
				stacks.setCanBorrow(true);
			}
			if(stacksservice.tryToChangeCanBorrow(stacks)) {
//				System.out.println("aaa");
				result = "success";
			}
			else {
				result = "fail";
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
