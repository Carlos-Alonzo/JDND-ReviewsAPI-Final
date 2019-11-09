package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Review;
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

    // Wire needed Mongo repositories here
//    @Autowired
//    private CommentRepository commentRepository;
    @Autowired
    private ReviewRepository reviewRepository;

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
        if(reviewRepository.findById(reviewId).isPresent())
        {
            Review theReview = reviewRepository.findById(reviewId).get();
            theReview.addComment(comment);
//            for (Comment c : reviewRepository.findAllCommentsById(theReview.getId()))
//                System.out.println(c.toString());
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
        if(reviewRepository.findById(reviewId).isPresent())
                    return reviewRepository.findAllCommentsById(reviewId);
        else
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }
}