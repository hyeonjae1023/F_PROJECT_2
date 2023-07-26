package com.moviement.service;

import java.util.List;

import com.moviement.container.Container;
import com.moviement.dao.MovieArticleDao;
import com.moviement.dto.MovieArticle;

public class MovieArticleService {
	private MovieArticleDao movieArticleDao;

	public MovieArticleService() {
		movieArticleDao = Container.movieArticleDao;
	}

	public List<MovieArticle> getForPrintMovieArticles() {
		return movieArticleDao.getMovieArticles();
	}

	public int write(int price, String title, String body) {
		MovieArticle movieArticle = new MovieArticle( price, title, body);
		return movieArticleDao.write(movieArticle);
	}

	public List<MovieArticle> getMovieArticles() {
		return movieArticleDao.getMovieArticles();
	}
	
	public MovieArticle getMovieArticle(int id) {
		return movieArticleDao.getMovieArticle(id);
	}
	
	public void delete(int id) {
		movieArticleDao.delete(id);
	}
}