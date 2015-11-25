package com.broduce.lide.desktop;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class NhacsoAlbumCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		// String body = get(url);
		// Pattern ptn = Pattern
		// .compile("flashvars.*(http://nhacso.net/flash[^\\&]+)");
		// Matcher mtc = ptn.matcher(body);
		Pattern ptn = Pattern.compile("\\.([^\\.]+)\\.html");
		Matcher mtc = ptn.matcher(url);
		if (mtc.find()) {
			String infoUrl = mtc.group(1);
			infoUrl = "http://nhacso.net/playlists/ajax-get-detail-playlist?dataId="
					+ URLEncoder.encode(infoUrl);
			String bd = get(infoUrl);
			System.out.println(infoUrl);
			Pattern ptnl = Pattern
					.compile("\\<name\\>\\<!\\[CDATA\\[(.*)\\]\\]");
			Matcher mtcl = ptnl.matcher(bd);
			// Pattern ptnm = Pattern
			// .compile("\\<mp3link\\>\\<!\\[CDATA\\[(.*)\\]\\]");
			Pattern ptnm = Pattern
					.compile("\\<link_mp3\\>\\<!\\[CDATA\\[(.*)\\]\\]");
			Matcher mtcm = ptnm.matcher(bd);
			mtcl.find();
			while (mtcl.find() && mtcm.find()) {
				result.add(new Info(mtcl.group(1), mtcm.group(1), "mp3"));
			}
		}
		return result;
	}

}
