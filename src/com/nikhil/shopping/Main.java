package com.nikhil.shopping;

import java.util.Map;

public class Main {
	private static StockList stockList=new StockList();

	public static void main(String[] args) {
		StockItem temp=new StockItem("Bread",10,14);
		stockList.addItem(temp);
		
		temp=new StockItem("Milk",30,78);
		stockList.addItem(temp);
		
		temp=new StockItem("Dates",300);
		stockList.addItem(temp);
		
		temp=new StockItem("Dryer",400,2);
		stockList.addItem(temp);
		
		temp=new StockItem("Pen",5,19);
		stockList.addItem(temp);
		temp.adjustStock(2);
		
		temp=new StockItem("Helmet",1800);
		stockList.addItem(temp);
		temp.adjustStock(2);
		
		temp=new StockItem("Nike",1000,8);
		stockList.addItem(temp);
		temp.adjustStock(-2);
		
//		System.out.println(temp);
//		System.out.println(stockList);
		
//		stockList.reserveStock("Helmet", 1);
//		System.out.println(stockList);
		
		Cart nikhilCart=new Cart("nikhil");
		sellItem(nikhilCart,"Helmet",1);
		sellItem(nikhilCart,"Bread",8);
		sellItem(nikhilCart,"Dates",4);
		sellItem(nikhilCart,"Dryer",2);
		sellItem(nikhilCart,"Nike",6);
		removeItem(nikhilCart,"Dryer",1);
		System.out.println(nikhilCart);
		
		Cart jonesCart=new Cart("jones");
		sellItem(jonesCart,"Helmet",1);
		sellItem(jonesCart,"Bread",4);
		sellItem(jonesCart,"Dryer",2);
		sellItem(jonesCart,"Pen",7);
		removeItem(jonesCart,"Dryer",2);
		System.out.println(jonesCart);
		
		

	}
	
	public static int sellItem(Cart cart,String item,int quantity) {
		StockItem stockItem=stockList.get(item);
		if(stockItem==null) {
			System.out.println("We don't sell this item");
		}
		if(stockList.reserveStock(item, quantity)!=0) {
			return cart.addToCart(stockItem, quantity);
		}
		return 0;
	}
	
	public static int removeItem(Cart cart,String item,int quantity) {
		StockItem stockItem=stockList.get(item);
		if(stockItem==null) {
			System.out.println("We don't sell this item");
		}
		if(cart.removeFromCart(stockItem, quantity)==quantity) {
			return stockList.unreserveStock(item, quantity);
		}
		return 0;
	}
	public void checkOut(Cart cart) {
		for(Map.Entry<StockItem, Integer> item: cart.getCartList().entrySet()) {
			stockList.sellStock(item.getKey().getName(),item.getValue());
		}
		cart.clearCart();
	}

}
