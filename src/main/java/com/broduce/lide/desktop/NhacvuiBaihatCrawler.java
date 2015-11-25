package com.broduce.lide.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class NhacvuiBaihatCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		System.out.println("bai hat nhac.vui.vn");
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		Pattern ptn = Pattern.compile("playlistfile': '([^']+)");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			String bd = get("http://nhac.vui.vn" + mtc.group(1));
			Pattern ptnl = Pattern.compile("jwplayer:file.*(http.+)\\]\\]");
			Matcher mtcl = ptnl.matcher(bd);
			Pattern titlePtn = Pattern
					.compile("\\<title\\>\\<!\\[CDATA\\[(.+)\\]\\]");
			Matcher titleMtc = titlePtn.matcher(bd);
			while (titleMtc.find() && mtcl.find()) {
				result.add(new Info(titleMtc.group(1), mtcl.group(1), "mp3"));
			}
		}
		return result;
	}

}