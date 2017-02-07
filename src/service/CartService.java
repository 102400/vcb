package service;

import java.sql.Connection;
import java.util.List;

import dao.BookDao;
import dao.CartDao;
import dao.StacksDao;
import entity.Book;
import entity.Cart;
import entity.Location;
import entity.Stacks;
import entity.User;
import show.CartShow;
import util.JDBC;

public class CartService {
	
	public CartShow cartShow(User user) {
		
		CartShow cartshow = new CartShow();
		
		Connection conn = JDBC.getConnection();
		
		try {
			CartDao cartdao = new CartDao();
			List<Object> list = cartdao.findCartByUserId(user, conn);
			if(list==null) {
				return null;
			}
			List<Cart> cartList = (List<Cart>) list.get(0);
			List<Book> bookList = (List<Book>) list.get(1);
			List<User> ownerList = (List<User>) list.get(2);
			List<Location> ownerlocationList = (List<Location>) list.get(3);
			if(cartList==null||bookList==null||ownerList==null||ownerlocationList==null) {
				return null;
			}
			cartshow.setCartList(cartList);
			cartshow.setBookList(bookList);
			cartshow.setOwnerList(ownerList);
			cartshow.setOwnerlocationList(ownerlocationList);
		}
		finally {
			JDBC.closeConnection(conn);
		}
		return cartshow;
	}
	
	public boolean addCart(User user,Stacks stacks) {
		
		Connection conn = JDBC.getConnection();
		
		try {
			CartDao cartdao = new CartDao();
			if(!cartdao.isCartExist(user, stacks, conn)) {
				return false;
				//存在直接返回
			}
			if(cartdao.addCart(user, stacks, conn)) {
				return true;
			}
			else {
				return false;
			}
		}
		finally {
			JDBC.closeConnection(conn);
		}
	}
	
	public boolean deleteCart(User user,Stacks stacks) {
		Connection conn = JDBC.getConnection();
		
		try {
			CartDao cartdao = new CartDao();
			if(cartdao.deleteCart(user, stacks, conn)) {
				return true;
			}
			else {
				return false;
			}
		}
		finally {
			JDBC.closeConnection(conn);
		}
	}

}
