package com.moviement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.moviement.container.Container;
import com.moviement.db.DBConnection;
import com.moviement.dto.MovieArticle;
import com.moviement.dto.Review;

public class ReviewDao {
	private DBConnection dbConnection;
	
	public ReviewDao() {
		dbConnection = Container.getDBConnection();
	}
	
	public int doWrite(Review review) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("INSERT INTO review "));
		sb.append(String.format("SET regDate = NOW(), "));
		sb.append(String.format("updateDate = NOW(), "));
		sb.append(String.format("title = '%s', ", review.title));
		sb.append(String.format("`body` = '%s', ", review.body));
		sb.append(String.format("`name` = '%s', ", review.name));
		sb.append(String.format("grades = %.1f ", review.grades));
		
		return dbConnection.insert(sb.toString());
	}
	
	public List<Review> getReviews() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("SELECT * FROM review"));
		List<Review> reviews = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());
		
		for (Map<String, Object> row : rows) {
			reviews.add(new Review(row));
		}
		return reviews;
	}
	
	public Review getForPrintReview(int reviewId) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM review "));
		sb.append(String.format("WHERE id = '%d' ", reviewId));
		
		Map<String, Object> row = dbConnection.selectRow(sb.toString());
	
		if (row.isEmpty()) {
			return null;
		}
		return new Review(row);
	}
	
	public List<Review> getForPrintReviews(String nickName) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM review "));
		sb.append(String.format("WHERE `name` = '%s' ", nickName));

		List<Review> reviews = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			reviews.add(new Review(row));
		}
		return reviews;
	}
	
	public int modifyReview(int id, String body, float grades) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("UPDATE review "));
		sb.append(String.format("SET updateDate = NOW(), "));
		sb.append(String.format("grades = '%.1f', ", grades));
		sb.append(String.format("body = '%s' ", body));
		sb.append(String.format("WHERE id = '%d' ", id));

		return dbConnection.update(sb.toString());
	}
	
	public Review getReview(int id) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM review "));
		sb.append(String.format("WHERE id = '%d' ", id));
		
		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}
		return new Review(row);
	}
	
	public int delete(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("DELETE FROM review "));
		sb.append(String.format("WHERE id = '%d' ", id));

		return dbConnection.delete(sb.toString());
	}
}