package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.OrderDao;
import dao.OrderItemDao;
import dao.OrderStateDao;
import dao.StacksDao;
import entity.Order;
import entity.Stacks;
import entity.User;
import util.JDBC;

public class OrderService {
	
	public Order changeState(Order order,int userId,int status,String u) {
		Connection conn = JDBC.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		try {
			OrderDao orderdao = new OrderDao();
			if("owner".equals(u)) {
				order = orderdao.findOrderByOrderIdOwner(order, conn);  //查找这个用户是否有权限操作
				if(order==null||order.getOwnerId()!=userId) {
					return null;
				}
				order.setState(status);
			}
			else if("holder".equals(u)) {
				order = orderdao.findOrderByOrderIdHolder(order, conn);  //查找这个用户是否有权限操作
				if(order==null||order.getPurchaserId()!=userId) {
					return null;
				}
				order.setState(status);
			}
			else {
				return null;
			}
			order = orderdao.changeStateHolder(order, conn);
			if(order==null) {
				JDBC.rollback(conn);
				return null;
			}
			OrderStateDao orderstatedao = new OrderStateDao();
			if(!orderstatedao.insertOrderState(order, conn)) {
				JDBC.rollback(conn);
				return null;
			}
			if(order.getState()==5||order.getState()==-99) {  //对stacks表isLoan进行操作
				OrderItemDao orderitemdao = new OrderItemDao();
				List<Stacks> stacksList = orderitemdao.findOrderItemByOrderId(order, conn);
				if(stacksList==null) {
					JDBC.rollback(conn);
					return null;
				}
				
				StacksDao stacksdao = new StacksDao();
				for(Stacks stacks:stacksList) {
					stacks.setLoan(true);
					stacks.setHolderId(userId);
					if(!stacksdao.changeISLoanAndholderId(stacks, conn)) {
						JDBC.rollback(conn);
						return null;
					}
				}
			}
			
			JDBC.commit(conn);
			return order;
			
		}
		finally {
			JDBC.closeConnection(conn);
		}
	}

	public boolean createOrder(List<Stacks> itemList,User user) {
		Connection conn = JDBC.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			//获取stacks
			Stacks stacks = itemList.get(0);
			StacksDao stacksdao = new StacksDao();
			stacks = stacksdao.findStacksByItemId(stacks, conn);
			if(stacks==null) {
				return false;
			}
			//插入order,,获取orderId
			Order order = new Order();
			order.setState(1);
			order.setOwnerId(stacks.getOwnerId());
			order.setPurchaserId(user.getUserId());
			order.setOwnerLocationId(stacks.getOwnerLocationId());
			OrderDao orderdao = new OrderDao();
			order = orderdao.insertOrder(order, conn);
			if(order==null) {
				JDBC.rollback(conn);
				return false;
			}
			//插入orderstate
			OrderStateDao orderstatedao = new OrderStateDao();
			if(!orderstatedao.insertOrderState(order, conn)) {
				JDBC.rollback(conn);
				return false;
			}
			
			OrderItemDao orderitemdao = new OrderItemDao();
			for(Stacks s:itemList) {  //修改状态
				s.setHolderId(user.getUserId());
				//插入orderitems
				if(!orderitemdao.insertOrderItem(order, s, conn)) {
					JDBC.rollback(conn);
					return false;
				}
				
				//修改stacks表holder,isLoan ?
				if(!stacksdao.changeISLoanAndholderId(s, conn)) {
					JDBC.rollback(conn);
					return false;
				}
				JDBC.commit(conn);
			}
		}
		finally {
			JDBC.closeConnection(conn);
		}
		return true;
	}
	
}