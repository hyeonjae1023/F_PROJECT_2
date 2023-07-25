package com.moviement.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Seat extends Dto {
	public String title;
	public String movieTitle;

	public Seat(String title) {
		this.title = title;
		this.movieTitle = movieTitle;
	}

	public Seat(Map<String, Object> row) {
		super(row);
		this.title = (String) row.get("title");
		this.movieTitle = (String) row.get("movieTitle");
	}
}