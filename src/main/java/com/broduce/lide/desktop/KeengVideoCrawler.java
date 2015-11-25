package com.broduce.lide.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class KeengVideoCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		String title = null;
		Pattern titlePtn = Pattern.compile("<title>([^\"|<]+)");
		Matcher titleMtc = titlePtn.matcher(body);
		if (titleMtc.find()) {
			title = titleMtc.group(1).trim();
		}
		Pattern ptn = Pattern.compile("file=(http[^&]+\\.mp4)");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			result.add(new Info(title, mtc.group(1), "mp4"));
		}
		return result;
	}

}
