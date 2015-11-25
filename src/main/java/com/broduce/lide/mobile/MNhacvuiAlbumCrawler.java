package com.broduce.lide.mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class MNhacvuiAlbumCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		Pattern ptn = Pattern
				.compile("title_page=\"([^\"]+)\".*url_download=\"([^\"]+)");
		Matcher mtc = ptn.matcher(body);
		while (mtc.find()) {
			result.add(new Info(mtc.group(1), mtc.group(2), "mp3"));
		}
		return result;
	}

}