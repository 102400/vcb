package service;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import dao.BookDao;
import dao.CartDao;
import dao.LocationDao;
import dao.SettingsDao;
import dao.StacksDao;
import entity.Book;
import entity.Location;
import entity.Stacks;
import entity.User;
import show.MyStacksShow;
import show.PeopleStacksShow;
import util.JDBC;

public class StacksService {
	
	//将购物车内容塞入HashSet
	public PeopleStacksShow peopleStacksShow(User user) {
		PeopleStacksShow peoplestacksshow = new PeopleStacksShow();
		Connection conn = JDBC.getConnection();
		try {
			CartDao cartdao = new CartDao();
			Set<Integer> cartItemId = cartdao.findCartItemIdByUserId(user, conn);
			if(cartItemId==null) {
				return null;
			}
			peoplestacksshow.setCartItemId(cartItemId);
		}
		finally {
			JDBC.closeConnection(conn);
		}
		
		return peoplestacksshow;
	}
	
	public MyStacksShow myStacksShow(User user) {
		MyStacksShow mystacksshow = new MyStacksShow();
		
		Connection conn = JDBC.getConnection();
		
		
		try {
			
			StacksDao stacksdao = new StacksDao();
//			List<Stacks> itemList = stacksdao.findItemByUserId(user,conn);
//			if(itemList==null) {
//				return null;
//			}
//			mystacksshow.setItemList(itemList);
			List<Object> list = stacksdao.findItemByUserId(user,conn);
			if(list==null||list.isEmpty()) {
				return null;
			}
			if(list.get(0)!=null) {
				mystacksshow.setItemList((List<Stacks>) list.get(0));
				if(list.get(1)!=null) {
					mystacksshow.setBookList((List<Book>) list.get(1));
				}
				else {
					return null;
				}
			}
			else {
				return null;
			}
			
//			BookDao bookdao = new BookDao();
//			Book book = new Book();
			
			LocationDao locationdao = new LocationDao();
			List<Location> locationList = locationdao.findLocationByUserId(user, conn);
			if(locationList==null) {
				return null;
			}
			mystacksshow.setLocationList(locationList);
			
		}
		finally {
			JDBC.closeConnection(conn);
		}
		
		return mystacksshow;
	}
	
	public boolean tryToChangeCanBorrow(Stacks stacks) {
		
		Connection conn = JDBC.getConnection();
		try {
			StacksDao stacksdao = new StacksDao();
			if(stacksdao.changeCanBorrow(stacks,conn)) {
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
	
	public boolean tryToDeleteItem(User user,Book book) {
		Connection conn = JDBC.getConnection();
		
		SettingsDao settingdao = new SettingsDao();
		StacksDao stacksdao = new StacksDao();
		
		
		try {
			
			Location location = settingdao.findDefaultLocationIdByUserId(user, conn);
			if(location==null) {
				return false;
			}
			 
			//还需检查此书是否已拥有  检查bookId locationId
			Stacks stacks = new Stacks();
			stacks.setOwnerLocationId(location.getLocationId());
			stacks.setBookId(book.getBookId());
//			if(!stacksdao.isItemExist(stacks, conn)) {
//				return false;  //不存在
//			}
			
			//
			if(stacksdao.deleteItem(user, book, location, conn)) {
				
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
	
	public boolean tryToCreateItem(User user,Book book) {
		Connection conn = JDBC.getConnection();
		
		SettingsDao settingdao = new SettingsDao();
		StacksDao stacksdao = new StacksDao();
		
		
		try {
			
			Location location = settingdao.findDefaultLocationIdByUserId(user, conn);
			if(location==null) {
				return false;
			}
			 
			//还需检查此书是否已拥有  检查bookId locationId
			Stacks stacks = new Stacks();
			stacks.setOwnerLocationId(location.getLocationId());
			stacks.setBookId(book.getBookId());
			if(stacksdao.isItemExist(stacks, conn)) {
				return false;
			}
			
			if(stacksdao.insertItem(user, book, location, conn)) {
				
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
	
	
	//t
	public boolean isItemExist(User user,Book book) {
		
		Connection conn = JDBC.getConnection();
		
		SettingsDao settingdao = new SettingsDao();
		StacksDao stacksdao = new StacksDao();
		
		
		try {
			
			Location location = settingdao.findDefaultLocationIdByUserId(user, conn);
			if(location==null) {
				return false;
			}
			 
			//还需检查此书是否已拥有  检查bookId locationId
			Stacks stacks = new Stacks();
			stacks.setOwnerLocationId(location.getLocationId());
			stacks.setBookId(book.getBookId());
			if(stacksdao.isItemExist(stacks, conn)) {
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
