package test;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class A extends ActionSupport {
	
	private Box box = new Box();
	
	public Box getBox() {
		return box;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ActionContext.getContext().put("r", "r_a");
		ActionContext.getContext().getSession().put("s", "s_a");
		
//		Box box = new Box();
		for(int i=0;i<10;i++) {
			box.add(i+"i", i*10+"i*10", i);
		}
		
		
//		ActionContext.getContext().put("arr", box);
		
		return SUCCESS;
	}

}
