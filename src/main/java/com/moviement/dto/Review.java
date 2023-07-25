package com.moviement.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Review extends Dto {
	public String title;
	public String body;
	public String name;
	public float grades;

	public Review (String title, String body, String name, float grades) {
		this.title = title;
		this.body = body;
		this.name = name;
		this.grades = grades;
	}
	
	public Review(Map<String, Object> row) {
		super(row);
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.name = (String) row.get("name");
		this.grades = (float) row.get("grades");
	}
}