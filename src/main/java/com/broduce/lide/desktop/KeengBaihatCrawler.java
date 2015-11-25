package com.broduce.lide.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class KeengBaihatCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		String title = null;
		Pattern titlePtn = Pattern.compile("<title>([^\"|]+)");
		Matcher titleMtc = titlePtn.matcher(body);
		if (titleMtc.find()) {
			title = titleMtc.group(1);
		}
		Pattern ptn = Pattern.compile("(http://www.keeng.vn/hmobile.php[^&]+)");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			String bd = get(mtc.group(1));
			Pattern ptnl = Pattern.compile("file=(http[^&;]+\\.mp3)");
			Matcher mtcl = ptnl.matcher(bd);
			if (mtcl.find()) {
				result.add(new Info(title, mtcl.group(1), "mp3"));
			}
		}
		return result;
	}

}
