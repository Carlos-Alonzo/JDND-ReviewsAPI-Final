package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.repositories.CommentRepository;
import com.udacity.course3.reviews.repositories.ProductRepository;
import com.udacity.course3.reviews.repositories.ReviewRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPAProductRepoTests
{
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private CommentRepository commentRepository;


	@Test
	public void testInsertNewProduct()
	{
		Product testProduct = new Product("Lenovo", "Lenovo T450", "Lenovo t450 laptop", "123456789");
		Assert.assertNotNull(productRepository.save(testProduct));
	}

	@Test
	public void testFindProductById()
	{
		Product testProduct = productRepository.save(new Product("Lenovo", "Lenovo T450", "Lenovo t450 laptop", "123456789"));
		Assert.assertNotNull(testProduct.getProductId());
	}


	@Test
	public void testRetrieveAllProducts()
	{
		Assert.assertNotNull(productRepository.findAll());
	}

	@Test
	public void testInsertNewReview()
	{
		Review testReview = new Review();
		testReview.setProduct(productRepository.save(new Product("Lenovo", "Lenovo T450", "Lenovo t450 laptop", "123456789")));
		testReview.setReviewer("Carlos A.");
		testReview.setRating(5);
		testReview.setTitle("Wonderful product");
		testReview.setText("I have not come across something this good");
		testReview.setReviewdate(new Timestamp(System.currentTimeMillis()));
		Assert.assertNotNull(reviewRepository.save(testReview));
	}

	@Test
	public void testInsertNewComment()
	{
		Comment testComment = new Comment();
		Review testReview = new Review();
		testReview.setProduct(productRepository.save(new Product("Lenovo", "Lenovo T450", "Lenovo t450 laptop", "123456789")));
		testReview.setReviewer("Carlos A.");
		testReview.setRating(5);
		testReview.setTitle("Wonderful product");
		testReview.setText("I have not come across something this good");
		testReview.setReviewdate(new Timestamp(System.currentTimeMillis()));
		testComment.setReview(reviewRepository.save(testReview));
		testComment.setCreatedon(new Timestamp(System.currentTimeMillis()).toString());
		testComment.setText(testReview.getText());
		testComment.setTitle("OMG");
		Assert.assertNotNull(commentRepository.save(testComment));
	}

	@Test
	public void testRetrieveAllReviews()
	{
		Assert.assertNotNull(reviewRepository.findAll());
	}

	@Test
	public void testRetrieveAllComments()
	{
		Assert.assertNotNull(commentRepository.findAll());
	}
}
