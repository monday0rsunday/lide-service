package com.broduce.lide.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class ChiasenhacCrawler extends AbstractCrawler {
	private Pattern titlePtn = Pattern.compile("<title>([^\"|<~]+)");
	private Pattern ptn = Pattern
			.compile("decodeURIComponent\\(\"([^\"]+\\.(mp3|mp4|m4a))\"");

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		String title = null;
		Matcher titleMtc = titlePtn.matcher(body);
		if (titleMtc.find()) {
			title = titleMtc.group(1).trim();
		}
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			try {
				String link = URLDecoder.decode(mtc.group(1), "UTF-8");
				if (link.endsWith("mp3")) {
					result.add(new Info(title, link, "mp3"));
				} else if (link.endsWith("mp4")) {
					result.add(new Info(title, link, "mp4"));
				} else if (link.endsWith("m4a")) {
					result.add(new Info(title, link, "m4a"));
				}
			} catch (UnsupportedEncodingException e) {
				Logger.getLogger(getClass()).warn("", e);
			}
		}
		return result;
	}

}
