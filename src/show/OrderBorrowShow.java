package show;

import java.util.ArrayList;
import java.util.List;

public class OrderBorrowShow {
	
//	public OrderBorrowShow() {
//		System.out.println("OrderBorrowShow");
//	}
	
	private List<OrderVat> orderList = new ArrayList<>();

	public List<OrderVat> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderVat> orderList) {
		this.orderList = orderList;
	}

}
