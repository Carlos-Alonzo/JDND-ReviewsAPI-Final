package com.udacity.course3.reviews.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.sql.Timestamp;

@Document
public class Comment
{
	public Comment()	{ createdon =  new Timestamp(System.currentTimeMillis()).toString();}

	public Comment(String reviewid, String id, String text)
	{
		this.reviewid=reviewid;
		this.id = id;
		this.text = text;
		createdon = new Timestamp(System.currentTimeMillis()).toString();
	}

	@Id
	private String id;
	private String reviewid;
	private String text;
	private String createdon;

	public String getReviewid() { return reviewid;	}
	public void setReviewid(String reviewid) {		this.reviewid = reviewid; }
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
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
}
