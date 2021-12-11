package main.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Type {
	private int id;
	private String name;
	
	public Type() {
		//Constructor here...
	}
	
	public Type(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
