package show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Book;
import entity.Cart;
import entity.Location;
import entity.User;

public class CartShow {

//	private int cartListSize;
	private List<Cart> cartList = new ArrayList<>();
	private List<Book> bookList = new ArrayList<>();
	private List<User> ownerList = new ArrayList<>();
	private List<Location> ownerlocationList = new ArrayList<>();
	private Map<Integer,List<Vat>> ownerLocationIdMap = new HashMap<>();
	
	public class Vat {  //必须为public
		
		private Cart cart;
		private Book book;
		private User owner;
		private Location ownerlocation;
		
		public Vat(Cart cart, Book book, User owner, Location ownerlocation) {
			this.cart = cart;
			this.book = book;
			this.owner = owner;
			this.ownerlocation = ownerlocation;
		}
		public Cart getCart() {
			return cart;
		}
		public void setCart(Cart cart) {
			this.cart = cart;
		}
		public Book getBook() {
			return book;
		}
		public void setBook(Book book) {
			this.book = book;
		}
		public User getOwner() {
			return owner;
		}
		public void setOwner(User owner) {
			this.owner = owner;
		}
		public Location getOwnerlocation() {
			return ownerlocation;
		}
		public void setOwnerlocation(Location ownerlocation) {
			this.ownerlocation = ownerlocation;
		}
		
	}
	
	public void initOwnerLocationIdMap() {
		
		for(int i=0;i<ownerlocationList.size();i++) {
			int locationId = ownerlocationList.get(i).getLocationId();
//			ownerLocationIdList.put(ownerlocationList.get(i).getLocationId(), (List<Location>) ownerlocationList.get(i));
			if(!ownerLocationIdMap.containsKey(locationId)) {  //此映射不包含指定键的映射关系
				ownerLocationIdMap.put(locationId,new ArrayList<Vat>());
			}
			List<Vat> vatList = ownerLocationIdMap.get(locationId);
			vatList.add(new Vat(cartList.get(i), bookList.get(i), ownerList.get(i), ownerlocationList.get(i)));
			ownerLocationIdMap.put(locationId, vatList);
			
		}
//		for(int aaa:ownerLocationIdList.keySet()) {
//		System.out.println(aaa + " -----");
//			for(Vat j:ownerLocationIdList.get(aaa)) {
//				System.out.println(j.getBook().getName());
//			}
//		}
		
//		for(int i=0;i<ownerlocationList.size();i++) {
//			int locationId = ownerlocationList.get(i).getLocationId();
////			ownerLocationIdList.put(ownerlocationList.get(i).getLocationId(), (List<Location>) ownerlocationList.get(i));
//			if(!ownerLocationIdList.containsKey(locationId)) {  //此映射不包含指定键的映射关系
//				ownerLocationIdList.put(locationId, new ArrayList<Location>());
//			}
//			List<Location> locationList = ownerLocationIdList.get(locationId);
//			locationList.add(ownerlocationList.get(i));
//			ownerLocationIdList.put(locationId, locationList);
//		}
//		System.out.println("!!!");
//		for(int i:ownerLocationIdList.keySet()) {
//			System.out.println(i + " -----");
//			for(Location j:ownerLocationIdList.get(i)) {
//				System.out.println(j.getLocationId() + ":" + j.getName());
//			}
//		}
//		System.out.println("@@@");
	}
	
	public Map<Integer, List<Vat>> getOwnerLocationIdMap() {
		return ownerLocationIdMap;
	}
//	public int getCartListSize() {
//		return cartList.size();
//	}
	public List<Cart> getCartList() {
		return cartList;
	}
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	public List<User> getOwnerList() {
		return ownerList;
	}
	public void setOwnerList(List<User> ownerList) {
		this.ownerList = ownerList;
	}
//	public List<Location> getOwnerlocationList() {
//		return ownerlocationList;
//	}
	public void setOwnerlocationList(List<Location> ownerlocationList) {
		this.ownerlocationList = ownerlocationList;
	}
	
}
