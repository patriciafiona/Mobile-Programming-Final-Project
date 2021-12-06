package main.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Color {
	private int id;
	private String name;
	private String color_code;
	
	public Color(int id, String name, String color_code) {
		super();
		this.id = id;
		this.name = name;
		this.color_code = color_code;
	}
}
