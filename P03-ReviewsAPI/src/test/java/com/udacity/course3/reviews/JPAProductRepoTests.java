package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAProductRepoTests
{
	@Autowired
	private ProductRepository productRepository;


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


}
