package main.entities;

import java.sql.Timestamp;

public class Transaction {
	private int id;
	private String transaction_id;
	private int user_id;
	private int product_id;
	private int quantity;
	private int color_id;
	private int discount;
	private double price;
	private int size;
	private int status_id;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public Transaction(int id, String transaction_id, int user_id, int product_id, int quantity, int color_id,
			int discount, double price, int size, int status_id, Timestamp created_at, Timestamp updated_at) {
		super();
		this.id = id;
		this.transaction_id = transaction_id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.color_id = color_id;
		this.discount = discount;
		this.price = price;
		this.size = size;
		this.status_id = status_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public Transaction(int id, String transaction_id, int user_id, int product_id, int quantity, int color_id,
			int discount, double price, int size, int status_id) {
		super();
		this.id = id;
		this.transaction_id = transaction_id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.color_id = color_id;
		this.discount = discount;
		this.price = price;
		this.size = size;
		this.status_id = status_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getColor_id() {
		return color_id;
	}
	public void setColor_id(int color_id) {
		this.color_id = color_id;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
}
