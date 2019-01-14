package com.jammin2shirts.TheRssed.Entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeedMessage {

	private String title;
	private String description;
	private String link;
	private String author;
	private String guid;
	private String pubDate;
	private String enclosure;

	private SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
	private SimpleDateFormat inputdfZone = new SimpleDateFormat("EEE, MM dd yyyy hh:mm:ss Z");
	private SimpleDateFormat inputdfNorm = new SimpleDateFormat("EEE, MM dd yyyy hh:mm:ss");

	
	
	
	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getLink() {
		return link;
	}




	public void setLink(String link) {
		this.link = link;
	}




	public String getAuthor() {
		return author;
	}




	public void setAuthor(String author) {
		this.author = author;
	}




	public String getGuid() {
		return guid;
	}




	public void setGuid(String guid) {
		this.guid = guid;
	}




	public String getPubDate() {
		return pubDate;
	}




	public void setPubDate(String pubDate) {
		
		Date date = null;
		try {
			if (pubDate.matches("[+]")) {
				date = inputdfZone.parse(pubDate);
			} else {
				date = inputdfNorm.parse(pubDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String strPubDate = df.format(date);
		this.pubDate = strPubDate;
	}




	public String getEnclosure() {
		return enclosure;
	}




	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}






	@Override
	public String toString() {
		return "FeedMessage [description=" + description + " link=" + link + ", enclosure=" + enclosure + ", pubDate="
				+ pubDate + ", title=" + title + ", author=" + author + ", guid=" + guid + "]";
	}

}
