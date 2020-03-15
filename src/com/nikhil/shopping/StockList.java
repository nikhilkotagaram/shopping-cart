package com.nikhil.shopping;

import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
	private Map<String,StockItem> items;
	
	public StockList() {
		this.items=new LinkedHashMap<>();
	}
	
	public StockItem get(String key) {
		return items.get(key);
	}
	public int addItem(StockItem item) {
		if(item!=null) {
			StockItem stock=items.getOrDefault(item.getName(), item);
			if(item!=stock) { //It means item already exits in our list
				item.adjustStock(stock.availableQuantity());
			}
			items.put(item.getName(),item);
			return item.availableQuantity();		
		}
		return 0;
	}
	public int reserveStock(String name,int quantity) {
		StockItem stock=items.get(name);
		if(stock!=null && quantity>0) {
			return stock.reserve(quantity);
		}
		return 0;
	}
	public int unreserveStock(String name,int quantity) {
		StockItem stock=items.get(name);
		if(stock!=null && quantity>0) {
			return stock.cancel(quantity);
		}
		return 0;
	}
	public int sellStock(String name,int quantity) {
		StockItem stock=items.get(name);
		if(stock!=null && quantity>0) {
			return stock.finalise(quantity);
		}
		return 0;
	}
	@Override
	public String toString() {
		double totalCost=0.0;
		String s="\n            StockList \n";
		for(Map.Entry<String,StockItem> stock:items.entrySet()) {
			StockItem item=stock.getValue();
			double itemCost=item.getPrice()*item.availableQuantity();
			s+=item+" Total item price:"+String.format("%.02f", itemCost)+"\n";
			totalCost+=itemCost;
	}
		return s+"Total Price of stock :"+totalCost;
	}
}
