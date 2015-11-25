package com.broduce.lide.mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class MNhaccuatuiCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		Pattern ptn = Pattern.compile("a href=\"([^\"]+mp3)\" title=\"([^\"]+)\"");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			result.add(new Info(mtc.group(2).replace("Nghe bài hát ", ""), mtc.group(1), "mp3"));
		}
		return result;
	}

}
