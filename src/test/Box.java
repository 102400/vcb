package test;

import java.util.ArrayList;
import java.util.List;

public class Box {
	
	private List<String> a = new ArrayList<>();
	private List<String> b = new ArrayList<>();
	private List<Integer> c = new ArrayList<>();
	private int endIndex = -1;
	
	public List<String> getA() {
		return a;
	}

	public List<String> getB() {
		return b;
	}

	public List<Integer> getC() {
		return c;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public Box() {
		
	}
	
	public void add(String a,String b,int c) {
		this.a.add(a);
		this.b.add(b);
		this.c.add(c);
		endIndex++;
	}
	
//	public static void main(String[] args) {
//		Box box = new Box();
//		String a = null;
//		String b = "b";
//		int c = 0;
//		box.add(a, b, c);
//	}

}
