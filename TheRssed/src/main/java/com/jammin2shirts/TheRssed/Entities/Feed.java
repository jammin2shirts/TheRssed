package com.jammin2shirts.TheRssed.Entities;

import java.util.ArrayList;
import java.util.List;

public class Feed {
	

	final String title;
	final String link;
	final String description;
	final String language;
	final String copyright;
	final String pubDate;
	final String enclosure;
	final List<FeedMessage> entries = new ArrayList<>();

	public Feed(String title, String link, String description, String language, String copyright, String pubDate, String enclosure) {
		this.title = title;
		this.copyright = copyright;
		this.description=description;
		this.language=language;
		this.link=link;
		this.pubDate=pubDate;
		this.enclosure=enclosure;
		
	}
	
	
	
	public String getTitle() {
		return title;
	}



	public String getLink() {
		return link;
	}



	public String getDescription() {
		return description;
	}



	public String getLanguage() {
		return language;
	}



	public String getCopyright() {
		return copyright;
	}



	public String getPubDate() {
		return pubDate;
	}



	public String getEnclosure() {
		return enclosure;
	}



	public List<FeedMessage> getEntries() {
		return entries;
	}



	@Override
	public String toString() {
		return "Feed [copyright="+copyright+ ", description="+description+ ", language=" +language+ ", link="+link+ ", enclosure="+enclosure+ ", pubDate="+pubDate+ ", title="+title+  "]";
	}
}
