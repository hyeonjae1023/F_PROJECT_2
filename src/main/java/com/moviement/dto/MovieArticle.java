package com.moviement.dto;

import java.util.Map;

import lombok.Data;

@Data
public class MovieArticle extends Dto {
	public String title;
	public String body;
//	public int memberId;
//	public int boardId;
	public int price;
	
	
	public MovieArticle(int price, String title, String body) {
		this( price, title, body, 0);
	}
	
	public MovieArticle( int price, String title, String body, int hit) {
	
		this.title = title;
		this.body = body;
		this.price = price;
	}
	
	public MovieArticle(Map<String, Object> row) {
		super(row);
		this.title = (String) row.get("title");
		this.body = (String) row.get("body");
		this.price = (int) row.get("price");
	}
}