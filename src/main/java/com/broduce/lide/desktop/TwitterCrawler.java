package com.broduce.lide.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class TwitterCrawler extends AbstractCrawler {

	Pattern iPtn = Pattern.compile("contentId=\"([^\"]+)");
	Pattern uPtn = Pattern.compile("data-amplify-playlist-url=\"([^\"]+)");
	Pattern rPtn = Pattern
			.compile("(?m)\\<MediaFile\\>[^<]+<!\\[CDATA\\[([^]]+)\\]\\]\\>");

	@Override
	public List<Info> crawl(String url) {
		url = url.replace("mobile.twitter.com", "twitter.com");
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		Matcher uMtc = uPtn.matcher(body);
		if (uMtc.find()) {
			String xmlUrl = uMtc.group(1);
			String xmlBody = get(xmlUrl);
			String title = "twitter";
			Matcher iMtc = iPtn.matcher(xmlBody);
			if (iMtc.find()) {
				title = iMtc.group(1);
			}
			Matcher rMtc = rPtn.matcher(xmlBody);
			while (rMtc.find()) {
				result.add(new Info(title, rMtc.group(1), "mp4"));
			}
		}
		return result;
	}
}