package com.udacity.course3.reviews.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="reviews")
public class Review
{
	public Review()	{ reviewdate = new Timestamp(System.currentTimeMillis());}

	public Review(int rating, String title, String text, String reviewer)
	{
		this.rating = rating;
		this.title = title;
		this.text = text;
		this.reviewer = reviewer;
		reviewdate = new Timestamp(System.currentTimeMillis());
	}

	@ManyToOne
	@JoinColumn(name ="productid", nullable = false)
	private Product product;// = new Product();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reviewid;

	@Column
	@NotNull
	private int rating;

	@Column
	@NotNull
	private String title;

	@Column
	@NotNull
	private String text;

	@Column
	@NotNull
	private String reviewer;

	@Column
	@NotNull
	private Timestamp reviewdate;

	public Date getReviewdate() { return reviewdate;	}

	public void setReviewdate(Timestamp reviewdate) 	{		this.reviewdate = reviewdate; }
	public String getTitle() { return title;	}
	public void setTitle(String title) 	{		this.title = title; }
	public long getReviewId()	{		return reviewid;	}
	public void setReviewId(int reviewId)	{		this.reviewid = reviewId;	}
	public int getRating()	{		return rating;	}
	public void setRating(int rating)	{		this.rating = rating; 	}
	public String getText() 	{		return text; 	}
	public void setText(String text) 	{		this.text = text; 	}
	public String getReviewer()	{		return reviewer;	}
	public void setReviewer(String reviewer) 	{		this.reviewer = reviewer; 	}
	public long getReviewid()
	{
		return reviewid;
	}
	public void setReviewid(long reviewid)
	{
		this.reviewid = reviewid;
	}
	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}
}
