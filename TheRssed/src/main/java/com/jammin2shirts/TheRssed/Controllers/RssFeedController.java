package com.jammin2shirts.TheRssed.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jammin2shirts.TheRssed.Entities.Feed;
import com.jammin2shirts.TheRssed.Utils.RSSFeedParser;

@Controller
public class RssFeedController {

	final static String joeRss="http://joeroganexp.joerogan.libsynpro.com/rss";
	final static String mangaRss="https://readms.net/rss";
	
	@GetMapping("/")
	public String retrieveFeed(@RequestParam(name="url", required=false, defaultValue=joeRss) String url, Model model) {
		RSSFeedParser parser = new RSSFeedParser(url);
		Feed feed = parser.readFeed();
		
		model.addAttribute("feed", feed);
		
		List<String> urls = new ArrayList<>();
		urls.add(joeRss);
		urls.add(mangaRss);
		
		model.addAttribute("urls", urls);
		return "index";
		
	}
	
}
