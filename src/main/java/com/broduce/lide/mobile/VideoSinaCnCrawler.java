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

public class VideoSinaCnCrawler extends AbstractCrawler {

	Pattern vPtn = Pattern.compile("\\<video id=\"video[^>]+src=\"([^\"]+)");
	Pattern titlePtn = Pattern.compile("<title>([^<]+)</title>");
	Map<String, String> headers = new HashMap<String, String>();

	public VideoSinaCnCrawler() {
		headers.put("User-Agent", UserAgent.getUserAgent(true));
	}

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url, headers);
		Matcher iMtc = vPtn.matcher(body);
		Matcher titleMtc = titlePtn.matcher(body);
		String title = "videosinavn";
		if (titleMtc.find()) {
			title = titleMtc.group(1);
		}
		if (iMtc.find()) {
			String videoUrl = iMtc.group(1);
			result.add(new Info(title, videoUrl, "mp4"));
		}
		return result;
	}
}
