package com.udacity.course3.reviews;

import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.repositories.CommentRepository;
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
public class MongoCommentRepoTests
{
	@Autowired
	private CommentRepository commentRepository;

	@Test
	public void testInsertNewComment()
	{
		//given
		Comment testComment = new Comment();
		testComment.setText("this comment belongs to review 5dc2e7bcc30fd6599ca0cded, it has been a great learning experience");
		testComment.setReviewid("5dc2e7bcc30fd6599ca0cded");
		commentRepository.save(testComment);
		//then
		Assert.assertNotNull(testComment.getId());
	}

	@Test
	public void testRetrieveAllReviews()
	{
		Assert.assertNotNull(commentRepository.findAll());
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
			MongoRepositoryFactoryBean mongoDbFactoryBean = new MongoRepositoryFactoryBean(CommentRepository.class);
			mongoDbFactoryBean.setMongoOperations(template);

			return mongoDbFactoryBean;
		}

		@Override
		public void destroy() throws Exception {
			executable.stop();
		}
	}

}

