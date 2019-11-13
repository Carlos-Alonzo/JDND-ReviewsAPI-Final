package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.documents.ReviewDoc;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.repositories.ProductRepository;
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
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // Wire JPA & Mongo repositories here
    @Autowired
    private ReviewDocRepository reviewDocRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productid The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Long productid, @Valid @RequestBody Review review)
    {
        if(productRepository.findById(productid).isPresent())
        {
            review.setProduct(productRepository.getOne(productid));
	        reviewRepository.save(review);

            ReviewDoc reviewDoc = new ReviewDoc(review.getRating(), review.getTitle(), review.getText(), review.getReviewer(), productid);
            reviewDoc.setId(Long.toString(review.getReviewId()));
            reviewDocRepository.save(reviewDoc);

            return new ResponseEntity<>(reviewDoc, HttpStatus.OK);
        }
        else
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Long productId)
    {
        if(productRepository.findById(productId).isPresent())
            return new ResponseEntity<>(reviewDocRepository.findAllReviewsByProductid(productId), HttpStatus.OK);
        else
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }
}