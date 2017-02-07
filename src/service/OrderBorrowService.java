package service;

import java.sql.Connection;
import java.util.List;

import dao.OrderDao;
import dao.OrderItemDao;
import entity.Book;
import entity.Location;
import entity.Order;
import entity.OrderItem;
import entity.Stacks;
import entity.User;
import show.OrderBorrowShow;
import show.OrderVat;
import util.JDBC;

public class OrderBorrowService {
	
	public OrderBorrowShow orderBorrowShow(User user,int who) {  //0为owner,1为holder
		OrderBorrowShow orderborrowshow = new OrderBorrowShow();
		
		Connection conn = JDBC.getConnection();
		
		try {
			OrderDao orderdao = new OrderDao();
			List<OrderVat> orderList = null;
			if(who==0) {
				orderList = orderdao.findOrderByOwnerId(user, conn);
			}
			else if(who==1) {
				orderList = orderdao.findOrderByPurchaserId(user, conn);
			}
			if(orderList==null) {
				return null;
			}
			
			for(OrderVat ov:orderList) {
				Order order = ov.getOrder();
				OrderItemDao orderitemdao = new OrderItemDao();
				
				List<Object> list = orderitemdao.findOrderItemByOrder(order, conn);
				if(list==null) {
					return null;
				}
				ov.setOrderItemList((List<OrderItem>) list.get(0));
				ov.setStacksList((List<Stacks>) list.get(1));
				ov.setBookList((List<Book>) list.get(2));
				ov.setOwnerList((List<User>) list.get(3));
				ov.setHolderList((List<User>) list.get(4));
				ov.setOwnerLocationList((List<Location>) list.get(5));
			}
			
			orderborrowshow.setOrderList(orderList);
		}
		finally {
			JDBC.closeConnection(conn);
		}
		
		return orderborrowshow;
	}

}
