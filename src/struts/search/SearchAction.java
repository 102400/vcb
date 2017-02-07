package struts.search;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.Book;
import entity.User;
import service.SearchService;
import service.StacksService;
import show.PeopleStacksShow;
import show.SearchBookInfoShow;
import show.SearchBookNameShow;
import show.SearchIsbnShow;

public class SearchAction extends ActionSupport {
	
	private String type;  //搜索提交类型
	private String q;  //搜索关键词
	private String message;  //提示消息
	
	private SearchBookNameShow searchbooknameshow;
	private SearchIsbnShow searchisbnshow;
	private SearchBookInfoShow searchbookinfoshow;
	private PeopleStacksShow peoplestacksshow;  //我的购物车内容
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	
	public String getMessage() {
		return message;
	}
	
	
	public SearchBookNameShow getSearchbooknameshow() {
		return searchbooknameshow;
	}
	
	public SearchIsbnShow getSearchisbnshow() {
		return searchisbnshow;
	}
	
	public SearchBookInfoShow getSearchbookinfoshow() {
		return searchbookinfoshow;
	}
	
	public PeopleStacksShow getPeoplestacksshow() {
		return peoplestacksshow;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		if(type==null||q==null) {
			return "404";
		}
		if(!(boolean) request.getAttribute("isLogin")) {
			return "needToLogin";
		}
		Book book = new Book();
		SearchService searchservice = new SearchService();
		if("bookname".equals(type)) {
			book.setName(q);
			searchbooknameshow = searchservice.searchBookNameShow(book);
			if(searchbooknameshow==null) {
				return "404";
			}
			return SUCCESS;
		}
		StacksService stacksservice = new StacksService();
		User user = new User();
		user.setUserId((int) request.getAttribute("userId"));
		peoplestacksshow = stacksservice.peopleStacksShow(user);
		if("isbn".equals(type)) {
			book.setIsbn(q);
			searchisbnshow = searchservice.searchIsbnShow(book);
			if(searchisbnshow==null) {
				return "404";
			}
			return SUCCESS;
		}
		if("bookinfo".equals(type)) {
			int bookinfoid;
			try {
				bookinfoid = Integer.valueOf(q);
				if(bookinfoid==1) {  //1为默认bookInfoId
					message = "此bookInfoId不可查询";
					return SUCCESS;
				}
			}
			catch(NumberFormatException e) {
				e.printStackTrace();
				message = "请输入bookInfoId";
				return SUCCESS;
			}
			book.setBookInfoId(bookinfoid);
			
			searchbookinfoshow = searchservice.searchBookInfoShow(book);
			if(searchbookinfoshow==null) {
				return "404";
			}
			return SUCCESS;
		}
		else {
			return "404";
		}
	}
	
}
