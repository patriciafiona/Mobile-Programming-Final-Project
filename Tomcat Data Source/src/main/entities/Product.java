package main.entities;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Product{
	private int productId;
	private int product_detail_id;
	private int category_id;
	private int type_id;
	private String type_name;
	private String name;
	private float price;
	private String description;
	private double rating;
	private String style;
	private String color_description;
	private int color_id;
	private int stock;
	private String photo_01;
	private String photo_02;
	private String photo_03;
	private String photo_04;
	private String photo_05;
	
	private String category_name;
	private String color_code;
	
	private int discount;
	
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public Product(int productId, int product_detail_id, int category_id, String name, float price, String description,
			double rating, String style, String color_description, int color_id, int stock, String photo_01,
			String photo_02, String photo_03, String photo_04, String photo_05) {
		super();
		this.productId = productId;
		this.product_detail_id = product_detail_id;
		this.category_id = category_id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.rating = rating;
		this.style = style;
		this.color_description = color_description;
		this.color_id = color_id;
		this.stock = stock;
		this.photo_01 = photo_01;
		this.photo_02 = photo_02;
		this.photo_03 = photo_03;
		this.photo_04 = photo_04;
		this.photo_05 = photo_05;
	}
	
	public Product(int productId, int product_detail_id, int category_id, String name, float price, String description,
			double rating, String style, String color_description, int stock, String photo_01,
			String photo_02, String photo_03, String photo_04, String photo_05,
			String category_name, String color_code, int type_id, String type_name, int discount,
			Timestamp created_at, Timestamp updated_at) {
		super();
		this.productId = productId;
		this.product_detail_id = product_detail_id;
		this.category_id = category_id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.rating = rating;
		this.style = style;
		this.color_description = color_description;
		this.stock = stock;
		this.photo_01 = photo_01;
		this.photo_02 = photo_02;
		this.photo_03 = photo_03;
		this.photo_04 = photo_04;
		this.photo_05 = photo_05;
		this.category_name = category_name;
		this.color_code = color_code;
		this.type_id = type_id;
		this.type_name = type_name;
		this.discount = discount;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}
