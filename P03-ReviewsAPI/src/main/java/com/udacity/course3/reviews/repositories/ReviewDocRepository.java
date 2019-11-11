package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.documents.ReviewDoc;
import com.udacity.course3.reviews.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewDocRepository extends MongoRepository<ReviewDoc, String>
{
	List<ReviewDoc> findAllReviewsByProductid(long productid);
	Optional<ReviewDoc> findById(String id);
	List<Comment> findAllCommentsById(String id);
}
