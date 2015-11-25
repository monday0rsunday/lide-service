package com.broduce.lide.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class NhaccuatuiCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		Pattern ptn = Pattern
				.compile("flashvars.*(http://www.nhaccuatui.com/flash/xml[^\"]+)");
		Matcher mtc = ptn.matcher(body);

		if (mtc.find()) {
			String infoUrl = mtc.group(1);
			String infoBody = get(infoUrl);
			Pattern ptnl = Pattern.compile(
					"\\<title\\>\\s+\\<!\\[CDATA\\[(.*)\\]\\]",
					Pattern.MULTILINE);
			Matcher mtcl = ptnl.matcher(infoBody);
			Pattern ptnm = Pattern.compile(
					"\\<location\\>\\s+\\<!\\[CDATA\\[(.*)\\]\\]",
					Pattern.MULTILINE);
			Matcher mtcm = ptnm.matcher(infoBody);
			while (mtcl.find() && mtcm.find()) {
				result.add(new Info(mtcl.group(1), mtcm.group(1), "mp3"));
			}
		}
		return result;
	}

}
