package struts.book;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.BookDao;
import entity.Book;
import entity.User;
import service.BookService;
import service.StacksService;
import show.BookShow;

public class BookAction extends ActionSupport {
	
	private BookShow bookshow;
	
	public BookShow getBookshow() {
		return bookshow;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		
		//String namespace = ServletActionContext.getActionMapping().getNamespace(); 
		String action_name = ServletActionContext.getActionMapping().getName();
		
		//System.out.println(namespace);
		//System.out.println(action);
		int bookId;
		try {
			bookId = Integer.valueOf(action_name);
		}
		catch(NumberFormatException e) {
			return "404";  //没有actionmapping 使用web.xml中的<error-page>
		}
		//System.out.println(bookId);
		BookService bookservice = new BookService();
		bookshow = bookservice.showBook(bookId);  //book信息
		if(bookshow==null) {
			return "404";
		}
		
		//t
		StacksService stacksservice = new StacksService();
		int userId = (int) ServletActionContext.getRequest().getAttribute("userId");
//		int userId = (int)ActionContext.getContext().get("userId");
//		System.out.println(userId);
		if(userId>0) {
			User user = new User();
			user.setUserId(userId);
			Book book = new Book();
			book.setBookId(bookId);
			boolean itemExist = stacksservice.isItemExist(user, book);
			ActionContext.getContext().put("own", itemExist);  //用户默认位置拥有此书
//			System.out.println(itemExist);
		}
		else {
			ActionContext.getContext().put("own", false);
		}
		
//		stacksservice.isItemExist(user, book);
		
		ActionContext.getContext().put("bookshow", bookshow);
		
		
		
		return SUCCESS;
	}
	
//	private boolean verify(Cookie[] cookies) {
//		
//		return false;
//	}
	
}
