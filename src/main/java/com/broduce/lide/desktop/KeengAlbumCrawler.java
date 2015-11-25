package com.broduce.lide.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class KeengAlbumCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		Pattern ptn = Pattern.compile("albumPlayerXml\\s+=\\s+'([^']+)");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			String bd = get("http://www.keeng.vn" + mtc.group(1));
			Pattern ptnl = Pattern.compile("\\<title\\>([^<]+)");
			Matcher mtcl = ptnl.matcher(bd);
			Pattern ptnm = Pattern.compile("\\<location\\>([^<]+)");
			Matcher mtcm = ptnm.matcher(bd);
			mtcl.find();
			while (mtcl.find() && mtcm.find()) {
				result.add(new Info(mtcl.group(1), mtcm.group(1), "mp3"));
			}
		}
		return result;
	}

}
