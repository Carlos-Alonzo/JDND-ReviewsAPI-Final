package com.udacity.course3.reviews;

import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import com.udacity.course3.reviews.documents.ReviewDoc;
import com.udacity.course3.reviews.repositories.ReviewDocRepository;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@DataMongoTest(excludeAutoConfiguration = {EmbeddedMongoAutoConfiguration.class})
@ExtendWith(SpringExtension.class)
public class MongoReviewDocDocRepoTests
{
	@Autowired
	private ReviewDocRepository reviewDocRepository;


	@Test
	public void testInsertNewReviewDoc()
	{
		//given
		ReviewDoc testReviewDoc = new ReviewDoc();
		testReviewDoc.setProductid(123456789l);
		testReviewDoc.setReviewer("Carlos A.");
		testReviewDoc.setRating(5);
		testReviewDoc.setTitle("Wonderful product");
		testReviewDoc.setText("Wonderful product");
		testReviewDoc.setId(testReviewDoc.getReviewdate());
		reviewDocRepository.save(testReviewDoc);
		//then
		Assert.assertNotNull(testReviewDoc.getId());
	}

	@Test
	public void testRetrieveAllReviewDocs()
	{
		Assert.assertNotNull(reviewDocRepository.findAll());
	}

	@Configuration
	static class MongoConfiguration implements InitializingBean, DisposableBean
	{

		MongodExecutable executable;

		@Override
		public void afterPropertiesSet() throws Exception {
			String host = "localhost";
			int port = 27017;

			IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
					                             .net(new Net(host, port, Network.localhostIsIPv6()))
					                             .build();

			MongodStarter starter = MongodStarter.getDefaultInstance();
			executable = starter.prepare(mongodConfig);
			executable.start();
		}


		@Bean
		public MongoDbFactory factory() {
			Long time = System.currentTimeMillis();

			MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClientURI("mongodb://localhost:27017/" + time.toString()));
			return mongoDbFactory;
		}


		@Bean
		public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
			MongoTemplate template = new MongoTemplate(mongoDbFactory);
			template.setWriteConcern(WriteConcern.ACKNOWLEDGED);
			return template;
		}

		@Bean
		public MongoRepositoryFactoryBean mongoFactoryRepositoryBean(MongoTemplate template) {
			MongoRepositoryFactoryBean mongoDbFactoryBean = new MongoRepositoryFactoryBean(ReviewDocRepository.class);
			mongoDbFactoryBean.setMongoOperations(template);

			return mongoDbFactoryBean;
		}

		@Override
		public void destroy() throws Exception {
			executable.stop();
		}
	}

}

