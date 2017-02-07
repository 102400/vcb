package show;

import java.util.HashSet;
import java.util.Set;

public class PeopleStacksShow {

	//存放用户购物车里的itemId
	private Set<Integer> cartItemId = new HashSet<>();

	public Set<Integer> getCartItemId() {
//		for(int i:cartItemId) {
//			System.out.println(i);
//		}
		return cartItemId;
	}

	public void setCartItemId(Set<Integer> cartItemId) {
		this.cartItemId = cartItemId;
	}

//	public int getItemId() {
//		return cartItemId;
//	}
	
	
	
	
}
