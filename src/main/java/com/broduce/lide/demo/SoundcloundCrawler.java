package com.broduce.lide.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class SoundcloundCrawler extends AbstractCrawler {
	private Pattern titlePtn = Pattern.compile("<title>([^\"|<~]+)");
	private Pattern ptn = Pattern
			.compile("twitter:player\".*content=[^&]+tracks%2F([^&]+)");

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url.replace("m.soundcloud", "soundcloud"));
		String title = null;
		Matcher titleMtc = titlePtn.matcher(body);
		if (titleMtc.find()) {
			title = titleMtc.group(1).replaceAll("\\\\u[0-9]+", "")
					.replaceAll("[^a-zA-Z0-9 ]", "").replaceAll(" +", " ")
					.trim();
		}
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			try {
				JSONObject obj = new JSONObject(
						get("https://api.soundcloud.com/i1/tracks/"
								+ mtc.group(1)
								+ "/streams?client_id=02gUJC0hH2ct1EGOcYXQIzRFU91c72Ea&app_version=d285b28"));
				result.add(new Info(title, obj.getString("http_mp3_128_url"),
						"mp3"));
			} catch (JSONException e) {
				Logger.getLogger(getClass()).warn("", e);
			}
		}
		return result;
	}

}
