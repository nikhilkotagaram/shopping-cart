package com.nikhil.shopping;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Cart {
	private String name;
	private Map<StockItem,Integer> cartList;
	
	public Cart(String name) {
		this.name=name;
		this.cartList=new TreeMap<>();
	}
	
	
	public String getName() {
		return name;
	}
	
	public int addToCart(StockItem item,int quantity) {
		if(item!=null && quantity>0) {
			int inCart=cartList.getOrDefault(item,0);
			cartList.put(item,inCart+quantity);
			return inCart;
		}
		return 0;
	}
	
	public int removeFromCart(StockItem item,int quantity){
		if(item!=null && quantity>0) {
			int inCart=cartList.getOrDefault(item, 0);
			int present=inCart-quantity;
			if(present>0) {
				cartList.put(item,present);
				return quantity;
			}
			if(present==0) {
				cartList.remove(item);
				return quantity;
			}
		}
		return 0;
	}
	
	public void clearCart() {
		cartList.clear();
	}
	public Map<StockItem, Integer> getCartList() {
		return Collections.unmodifiableMap(cartList);
	}
	
	@Override
	public String toString() {
		String s="\n         Contents of "+name+" Cart\n";
		double cartValue=0.0;
		for(Map.Entry<StockItem, Integer> cartItem:cartList.entrySet()) {
			StockItem item=cartItem.getKey();
			double value=item.getPrice()*cartItem.getValue();
			s+=item+" of which you reserved "+cartItem.getValue()+" total cost of item :"+String.format("%.2f", value)+"\n";
			cartValue+=value;
		}
		return s+"Total Value of Cart "+cartValue;
	}
	
	
}
