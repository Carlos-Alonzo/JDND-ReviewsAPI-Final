package com.udacity.course3.reviews.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name="comments")
public class Comment
{
	public Comment()	{ createdon =  new Timestamp(System.currentTimeMillis()).toString();}

	public Comment(String title, String text)
	{
		this.title=title;
		this.text = text;
		createdon = new Timestamp(System.currentTimeMillis()).toString();
	}

	@ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name = "reviewid", nullable = false)
	private Review review; // = new Review();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name ="commentid")
	private long id;
	@Column
	@NotNull
	private String title;
	@Column
	@NotNull
	private String text;
	@Column
	@NotNull
	private String createdon;

	public String getTitle() {		return title;	}
	public void setTitle(String title) 	{ this.title = title; }
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	public String getCreatedon()
	{
		return createdon;
	}
	public void setCreatedon(String createdon)
	{
		this.createdon = createdon;
	}
	public Review getReview() 	{ 		return review; }
	public void setReview(Review review) 	{ 		this.review = review; }
	public long getId()	{ 		return id;  }
	public void setId(long id) 	{		this.id = id; }

	@Override
	public String toString(){return "Title: " + title + " \n. Comment: " + text+ ", created on:" + createdon;}
}
