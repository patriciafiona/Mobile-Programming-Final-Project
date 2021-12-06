package main.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Category {
	private int id;
	private String name;
	
	public Category() {
		//Constructor here...
	}
	
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
