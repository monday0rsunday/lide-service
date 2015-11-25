package com.broduce.lide.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class PhimmoiCrawler extends AbstractCrawler {
	private Pattern titlePtn = Pattern.compile("<title>([^\"|<~]+)");
	private Pattern ptn = Pattern
			.compile("script\\s+async=\"true\"\\s+src=\"([^\"]+)");
	private Pattern jsonPtn = Pattern.compile("(?m)_responseJson='(.+)';$");

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		Matcher mtc = ptn.matcher(body);
		Matcher titleMtc = titlePtn.matcher(body);
		String title = null;
		if (titleMtc.find()) {
			title = titleMtc.group(1).trim();
		}
		if (mtc.find()) {
			try {
				String jsBody = get("http://www.phimmoi.net/" + mtc.group(1));
				Matcher jsonMtc = jsonPtn.matcher(jsBody);
				if (jsonMtc.find()) {
					JSONObject jsonObj = new JSONObject(jsonMtc.group(1));
					JSONArray jsonArr = jsonObj.getJSONArray("medias");
					for (int i = 0; i < jsonArr.length(); i++) {
						JSONObject itemObj = jsonArr.getJSONObject(i);
						if (itemObj.getString("type").equals("mp4")) {
							result.add(new Info(title + " "
									+ itemObj.getInt("resolution") + "p",
									itemObj.getString("url"), "mp4"));
						}
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).warn("", e);
			}
		}
		return result;
	}

}
