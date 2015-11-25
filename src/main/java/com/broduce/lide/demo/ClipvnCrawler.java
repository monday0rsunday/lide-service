package com.broduce.lide.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class ClipvnCrawler extends AbstractCrawler {
	private Pattern titlePtn = Pattern.compile("<title>([^\"|<~]+)");
	private Pattern ptn = Pattern.compile("'file':'([^']+)','label':'([^']+)'");

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		String title = null;
		Matcher titleMtc = titlePtn.matcher(body);
		if (titleMtc.find()) {
			title = titleMtc.group(1).replaceAll("[^a-zA-Z0-9 ]", "")
					.replaceAll(" +", " ").trim();
		}
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			result.add(new Info(title + " " + mtc.group(2), mtc.group(1), "mp4"));
		}
		return result;
	}

}
