package com.moviement.service;

import java.util.List;

import com.moviement.container.Container;
import com.moviement.dao.ReviewDao;
import com.moviement.dto.MovieArticle;
import com.moviement.dto.Review;

public class ReviewService {
	private ReviewDao reviewDao;

	public ReviewService() {
		reviewDao = Container.reviewDao;
	}
	
	public Review getReview(int id) {
		return reviewDao.getReview(id);
	}
	
	public Review getForPrintReview(int reviewId) {
		return reviewDao.getForPrintReview(reviewId);
	}
	
	public List<Review> getForPrintReviews(String nickName) {
		return reviewDao.getForPrintReviews(nickName);
	}

	public List<Review> getReviews() {
		return reviewDao.getReviews();
	}
	
	public void modifyReview(int id, String body, float grades) {
		reviewDao.modifyReview(id, body, grades);
	}
	
	public int write(String reviewTitle,String body, String name, float grades) {
		Review review = new Review(reviewTitle, body, name, grades);
		return reviewDao.doWrite(review);
	}
	
	public void delete(int id) {
		reviewDao.delete(id);
	}
}