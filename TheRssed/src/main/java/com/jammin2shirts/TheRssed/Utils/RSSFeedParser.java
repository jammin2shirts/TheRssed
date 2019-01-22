package com.jammin2shirts.TheRssed.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import com.jammin2shirts.TheRssed.Entities.Feed;
import com.jammin2shirts.TheRssed.Entities.FeedMessage;

public class RSSFeedParser {

	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String CHANNEL= "channel";
	private static final String LANGUAGE= "language";
	private static final String COPYRIGHT= "copyright";
	private static final String LINK= "link";
	private static final String ENCLOSURE= "enclosure";
	private static final String AUTHOR= "author";
	private static final String ITEM= "item";
	private static final String PUB_DATE= "pubDate";
	private static final String GUID= "guid";
	private final URL url;
	
	
	public RSSFeedParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Feed readFeed() {
		Feed feed = null;
		
		try {
			boolean isFeedHeader = true;
			String description = "";
			String title = "";
			String link= "";
			String language= "";
			String copyright= "";
			String author= "";
			String pubdate= "";
			String guid= "";
			String enclosure= "";
			
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				
				if (event.isStartElement()) {
					String localPart  = event.asStartElement().getName().getLocalPart();
					
					switch(localPart) {
					case ITEM:
						if (isFeedHeader) {
							isFeedHeader=false;
							feed = new Feed(title, link, description, language, copyright, pubdate, enclosure);
						}
						event = eventReader.nextEvent();
						break;
					case TITLE:
						title=getCharaterDate(event, eventReader);
						break;
					case DESCRIPTION:
						description=getCharaterDate(event, eventReader);
						break;
					case LINK:
						link=getCharaterDate(event, eventReader);
						break;
					case GUID:
						guid=getCharaterDate(event, eventReader);
						break;
					case LANGUAGE:
						language=getCharaterDate(event, eventReader);
						break;
					case AUTHOR:
						author=getCharaterDate(event, eventReader);
						break;
					case PUB_DATE:
						pubdate=getCharaterDate(event, eventReader);
						break;
					case COPYRIGHT:
						copyright=getCharaterDate(event, eventReader);
						break;
					case ENCLOSURE:
						Iterator<Attribute> iterator = event.asStartElement().getAttributes();
						
						while(iterator.hasNext()) {
							Attribute attr = iterator.next();
							String value = attr.getValue();
							String name = attr.getName().getLocalPart();
							
							if("url".equals(name)) {
								enclosure = value;
							}
						}
						break;
					}
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart()==(ITEM)) {
						FeedMessage message = new FeedMessage();
						message.setAuthor(author);
						message.setDescription(description);
						message.setEnclosure(enclosure);
						message.setGuid(guid);
						message.setLink(link);
						message.setPubDate(pubdate);
						message.setTitle(title);
						feed.getEntries().add(message);
						event = eventReader.nextEvent();
						continue;
					}
				}
			}
		} catch (XMLStreamException e ) {
			throw new RuntimeException(e);
		}
		return feed;
	}
	
	private String getCharaterDate(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result="";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
