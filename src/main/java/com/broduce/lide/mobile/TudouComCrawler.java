package com.broduce.lide.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.UserAgent;
import com.broduce.lide.model.Info;

public class TudouComCrawler extends AbstractCrawler {

	Pattern idPtn = Pattern.compile("iid:[^0-9]*([0-9]+)");
	Pattern titlePtn = Pattern.compile("htitle[^']+'([^']+)");
	Pattern mp4segsPtn = Pattern.compile("mp4segs:[^0-9]*([0-9]+)");
	Map<String, String> headers = new HashMap<String, String>();

	public TudouComCrawler() {
		headers.put("User-Agent", UserAgent.getUserAgent(true));
	}

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url, headers);
		Matcher idMtc = idPtn.matcher(body);
		Matcher titleMtc = titlePtn.matcher(body);
		Matcher mp4segsMtc = mp4segsPtn.matcher(body);
		String title = "tudou.com_" + System.currentTimeMillis();
		if (titleMtc.find()) {
			title = titleMtc.group(1);
		}
		if (idMtc.find() && mp4segsMtc.find()) {
			String videoUrl = "http://vr.tudou.com/v2proxy/v2?it="
					+ idMtc.group(1) + "&st=" + mp4segsMtc.group(1) + "&pw=";
			result.add(new Info(title, videoUrl, "mp4"));
		}
		return result;
	}
}
