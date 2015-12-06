package com.broduce.lide.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class TvzingCrawler extends AbstractCrawler {

	private Pattern titlePtn = Pattern.compile("<title>([^\"|<]+)");
	private Pattern ptn = Pattern
			.compile("<source src=\"(http[^\"]+\\.mp4)\".*data-res=\"([0-9]+)\"");

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
		List<String> exists = new ArrayList<String>();
		while (mtc.find()) {
			if (!exists.contains(mtc.group(1))) {
				result.add(new Info(title + " " + mtc.group(2) + "p", mtc
						.group(1), "mp4"));
				exists.add(mtc.group(1));
			}
		}
		return result;
	}

}
