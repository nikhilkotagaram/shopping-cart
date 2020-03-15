package com.nikhil.shopping;

public class StockItem implements Comparable<StockItem>{
	private String name;
	private double price;
	private int quantityInStock;
	private int reserved=0;
	
	public StockItem(String name,double price) {
		this(name,price,0);
	}
	public StockItem(String name,double price,int quantityInStock) {
		this.name=name;
		this.price=price;
		this.quantityInStock=quantityInStock;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int availableQuantity() {
		return this.quantityInStock-reserved;
	}
	
	public int adjustStock(int quantity) {
		if(quantity!=0) {
			this.quantityInStock+=quantity;
			return quantity;
		}
		return 0;
	}
	public int getReserved() {
		return reserved;
	}

	public int reserve(int quantity) {
		if(quantity<=this.availableQuantity()) {
			reserved+=quantity;
			return quantity;
		}
		return 0;
	}
	public int cancel(int quantity) {
		if(quantity<=this.reserved) {
			reserved-=quantity;
			return quantity;
		}
		return 0;
	}
	public int finalise(int quantity) {
		if(quantity<=reserved) {
			this.quantityInStock-=reserved;
			reserved-=quantity;
			return quantity;
		}
		return 0;
	}
	@Override
	public int compareTo(StockItem obj) {

		return this.name.compareToIgnoreCase(obj.getName());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null && this.getClass()!=obj.getClass()) {
			return false;
		}
		return this.name.equals(((StockItem) obj).getName());
	}
	
	@Override
	public int hashCode() {
		
		return this.getName().hashCode()+2;
	}
	@Override
	public String toString() {
		return this.name+" each price "+this.price+" reserved "+this.reserved;
	}

}
