package com.moviement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.moviement.container.Container;
import com.moviement.db.DBConnection;
import com.moviement.dto.MovieArticle;

public class MovieArticleDao extends Dao {
	private DBConnection dbConnection;

	public MovieArticleDao() {
		dbConnection = Container.getDBConnection();
	}

	public int write(MovieArticle movieArticle) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO movieArticle "));
		sb.append(String.format("SET regDate = NOW(), "));
		sb.append(String.format("title = '%s', ", movieArticle.title));
		sb.append(String.format("`body` = '%s', ", movieArticle.body));
		sb.append(String.format("price = '%d' ", movieArticle.price));

		return dbConnection.insert(sb.toString());
	}

	public List<MovieArticle> getMovieArticles() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM movieArticle"));

		List<MovieArticle> movieArticles = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			movieArticles.add(new MovieArticle(row));
		}
		return movieArticles;
	}

	public MovieArticle getMovieArticle(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM movieArticle "));
		sb.append(String.format("WHERE id = '%d' ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}
		return new MovieArticle(row);
	}
	
	public int delete(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("DELETE FROM movieArticle "));
		sb.append(String.format("WHERE id = '%d' ", id));

		return dbConnection.delete(sb.toString());
	}
}