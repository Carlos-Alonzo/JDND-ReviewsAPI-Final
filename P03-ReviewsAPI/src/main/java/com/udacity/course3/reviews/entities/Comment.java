package com.udacity.course3.reviews.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.sql.Timestamp;

//@Document
public class Comment
{
	public Comment()	{ createdon =  new Timestamp(System.currentTimeMillis()).toString();}

	public Comment(String title, String text)
	{
		this.title=title;
		this.text = text;
		createdon = new Timestamp(System.currentTimeMillis()).toString();
	}

//	@Id
//	private String id;
//	private String reviewid;
	private String title;
	private String text;
	private String createdon;

//	public String getReviewid() { return reviewid;	}
//	public void setReviewid(String reviewid) {		this.reviewid = reviewid; }
//	public String getId()
//	{
//		return id;
//	}
//	public void setId(String id)
//	{
//		this.id = id;
//	}

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
	@Override
	public String toString(){return "Title: " + title + " \n. Comment: " + text+ ", created on:" + createdon;}
}
