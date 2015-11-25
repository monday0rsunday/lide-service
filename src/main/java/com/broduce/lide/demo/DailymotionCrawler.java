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
import com.broduce.lide.UserAgent;
import com.broduce.lide.model.Info;

public class DailymotionCrawler extends AbstractCrawler {
	private Pattern ptn = Pattern.compile("(?m)buildPlayer\\((.*)\\);$");

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url, UserAgent.getUserAgent(true), "");
		// System.out.println(body);
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			try {
				JSONObject jsonObj = new JSONObject(mtc.group(1));
				String title = jsonObj.getJSONObject("metadata").getString(
						"title");
				for (Object key : jsonObj.getJSONObject("metadata")
						.getJSONObject("qualities").keySet()) {
					JSONArray jsonArr = jsonObj.getJSONObject("metadata")
							.getJSONObject("qualities")
							.getJSONArray(key.toString());
					if (jsonArr.length() > 0) {
						for (int i = 0; i < jsonArr.length(); i++) {
							String link = jsonArr.getJSONObject(0).getString(
									"url");
							String type = jsonArr.getJSONObject(0).getString(
									"type");
							if (type.equals("video/mp4")) {
								result.add(new Info(title + " " + key + "p"
										+ (i + 1), link, "mp4"));
							}
						}
					}
				}
			} catch (JSONException e) {
				Logger.getLogger(getClass()).warn("", e);
			}
		}
		return result;
	}

}
