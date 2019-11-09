package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String>
{
	List<Review> findAllReviewsByProductid(long productid);
	Optional<Review> findById(String id);
	List<Comment> findAllCommentsById(String id);
}
