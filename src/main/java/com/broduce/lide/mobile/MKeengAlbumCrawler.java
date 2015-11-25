package com.broduce.lide.mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class MKeengAlbumCrawler extends AbstractCrawler {

	public MKeengAlbumCrawler() {
	}

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		Pattern ptnl = Pattern
				.compile("title=\"([^\"]+)\" href=\"(http[^\"]+.mp3)\"");
		Matcher mtcl = ptnl.matcher(body);
		while (mtcl.find()) {
			result.add(new Info(mtcl.group(1), mtcl.group(2), "mp3"));
		}
		return result;
	}

}
