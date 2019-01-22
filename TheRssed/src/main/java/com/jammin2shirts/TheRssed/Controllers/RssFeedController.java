package com.jammin2shirts.TheRssed.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jammin2shirts.TheRssed.Entities.Feed;
import com.jammin2shirts.TheRssed.Utils.RSSFeedParser;

@Controller
public class RssFeedController {

	final static String joeRss="http://joeroganexp.joerogan.libsynpro.com/rss";
	
	@GetMapping("/rssFeeds")
	public String retrieveFeed(@RequestParam(name="url", required=false, defaultValue=joeRss) String url, Model model) {
		RSSFeedParser parser = new RSSFeedParser(url);
		Feed feed = parser.readFeed();
		model.addAttribute("feed", feed);
		
		
		
		return "DisplayRssFeeds";
		
	}
	
	@GetMapping("/tester")
	public String tester() {
		return "tester";
	}
}
