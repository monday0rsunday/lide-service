package com.broduce.lide.mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class MMp3zingVideoCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		String title = null;
		Pattern titlePtn = Pattern.compile("<h1>([^<]+)");
		Matcher titleMtc = titlePtn.matcher(body);
		if (titleMtc.find()) {
			title = titleMtc.group(1);
		}
		Pattern ptn = Pattern.compile("<source src=\"([^\"]+)");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			result.add(new Info(title, mtc.group(1), "mp4"));
		}
		return result;
	}

}
