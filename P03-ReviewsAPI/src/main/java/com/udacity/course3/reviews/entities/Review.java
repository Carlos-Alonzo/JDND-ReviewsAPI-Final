package com.udacity.course3.reviews.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Document
public class Review
{
	public Review()	{ reviewdate = new Timestamp(System.currentTimeMillis()).toString();  Comments = new ArrayList<>();}

	public Review(int rating, String title, String text, String reviewer, long productid)
	{
		this.rating = rating;
		this.title = title;
		this.text = text;
		this.reviewer = reviewer;
		this.productid=productid;
		reviewdate = new Timestamp(System.currentTimeMillis()).toString();
		Comments = new ArrayList<>();
	}

	@Id
	private String id;
	private int rating;
	private String title;
	private String text;
	private String reviewer;
	private String reviewdate;
	private long productid;
	private List<Comment> Comments;

	public long getProductid() 	{		return productid; }
	public void setProductid(long productid) 	{		this.productid = productid; }
	public String getReviewdate() { return reviewdate;	}
	public void setReviewdate(String reviewdate) 	{		this.reviewdate = reviewdate; }
	public String getTitle() { return title;	}
	public void setTitle(String title) 	{		this.title = title; }
	public String getText() { return text;	}
	public void setText(String text) 	{		this.text = text; }
	public String getId()	{		return id;	}
	public void setId(String id)	{		this.id = id;	}
	public int getRating()	{		return rating;	}
	public void setRating(int rating)	{		this.rating = rating; 	}
	public String getReviewer()	{		return reviewer;	}
	public void setReviewer(String reviewer) 	{		this.reviewer = reviewer; 	}
	public void addComment(Comment newComment) {Comments.add(newComment); }
	public List<Comment> getComments(){return Comments;}

	@Override
	public String toString() {return "Review id: " + id +
			                                     ", rating: " + rating +", title: " + title +
			                                     ", text: "+ text +", reviewer: " + reviewer +
			                                     ", productid: "+ productid + ", date: " + reviewdate;  }
}
