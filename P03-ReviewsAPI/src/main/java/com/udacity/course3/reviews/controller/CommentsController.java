package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.documents.ReviewDoc;
import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.repositories.CommentRepository;
import com.udacity.course3.reviews.repositories.ReviewDocRepository;
import com.udacity.course3.reviews.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.List;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController
{

    // Wire needed Mongo and JPA repositories here
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewDocRepository reviewDocRepository;
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") String reviewId, @Valid @RequestBody Comment comment)
    {
        if(reviewDocRepository.findById(reviewId).isPresent())
        {
            ReviewDoc reviewDoc = reviewDocRepository.findById(reviewId).get();
            reviewDoc.addComment(comment);

            comment.setReview();
            return  ResponseEntity.status(HttpStatus.OK).body(null);
        }
        else
        throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<Comment> listCommentsForReview(@PathVariable("reviewId") String reviewId)
    {
        if(reviewDocRepository.findById(reviewId).isPresent())
                    return reviewDocRepository.findAllCommentsById(reviewId);
        else
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }
}