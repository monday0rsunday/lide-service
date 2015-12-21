package com.broduce.lide.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.UserAgent;
import com.broduce.lide.model.Info;

public class VimeoCrawler extends AbstractCrawler {
	private Pattern ptn = Pattern
			.compile("function\\(e,a\\)\\{var t=(.+);if\\(!t.request\\)\\{return\\}");

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("User-Agent", UserAgent.getUserAgent(true));
		String body = get(
				url.replace("//vimeo.com", "//player.vimeo.com/video"),
				headerMap);
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			try {
				JSONObject jsonObj = new JSONObject(mtc.group(1));
				String title = jsonObj.getJSONObject("video")
						.getString("title");
				JSONArray jsonArr = jsonObj.getJSONObject("request")
						.getJSONObject("files").getJSONArray("progressive");
				for (int i = 0; i < jsonArr.length(); i++) {
					if (jsonArr.getJSONObject(i).getString("mime")
							.equals("video/mp4")) {
						result.add(new Info(
								title
										+ " "
										+ jsonArr.getJSONObject(i).getString(
												"quality"), jsonArr
										.getJSONObject(i).getString("url"),
								"mp4"));
					}
				}
			} catch (JSONException e) {
				Logger.getLogger(getClass()).warn("", e);
			}
		}
		return result;
	}

}
