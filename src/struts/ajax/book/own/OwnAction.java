package struts.ajax.book.own;

import com.opensymphony.xwork2.ActionSupport;

import entity.Book;
import entity.User;
import rule.VerifySource;
import service.StacksService;

public class OwnAction extends ActionSupport {
	
	private String own;  //true已拥有,false未拥有
	private int bookId;
	private int userId;
	private String verify;
	private String result;
	
	
	public String getOwn() {
		return own;
	}
	public void setOwn(String own) {
		this.own = own;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
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
		
		User user = new User();
		user.setUserId(userId);
		Book book = new Book();
		book.setBookId(bookId);
		
		if("false".equals(own)) {  //增加item
			if(stacksservice.tryToCreateItem(user, book)){
				result = "success";
			}
			else {
				result = "fail";
			}
		}
		else if("true".equals(own)) {  //删除
			if(stacksservice.tryToDeleteItem(user, book)){
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
