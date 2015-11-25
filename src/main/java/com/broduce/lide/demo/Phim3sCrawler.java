package com.broduce.lide.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.log4j.Logger;
//import org.json.JSONException;
//import org.json.JSONObject;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class Phim3sCrawler extends AbstractCrawler {
	// private Pattern titlePtn = Pattern.compile("<title>Xem ([^\"|<~]+)");
	// private Pattern ptn = Pattern.compile("videoUrl = '(.+)';");

	private Pattern urlPtn = Pattern
			.compile("http.*/([^/]+)/xem-phim/([0-9]+)/");

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		Matcher mtc = urlPtn.matcher(url);
		if (mtc.find()) {
			result.add(new Info(mtc.group(1), mtc.group() + "video.mp4", "mp4"));
		}
		return result;
	}
	// @Override
	// public List<Info> crawl(String url) {
	// List<Info> result = new ArrayList<Info>();
	// String body = get(url);
	// String title = null;
	// Matcher titleMtc = titlePtn.matcher(body);
	// if (titleMtc.find()) {
	// title = titleMtc.group(1).replaceAll(" +", " ").trim();
	// }
	// Matcher mtc = ptn.matcher(body);
	// if (mtc.find()) {
	// try {
	// result.add(new Info(title, mtc.group(1), "mp4"));
	// } catch (JSONException e) {
	// Logger.getLogger(getClass()).warn("", e);
	// }
	// }
	// return result;
	// }

}
