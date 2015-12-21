package com.broduce.lide.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.UserAgent;
import com.broduce.lide.model.Info;

public class MYoutubeCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("User-Agent", UserAgent.getUserAgent(true));
		String body = get(url, headerMap);
		System.out.println(body);
		Pattern titlePtn = Pattern
				.compile("\\\\\"title\\\\\": \\\\\"([^\"]+)\\\\\"");
		Matcher titleMtc = titlePtn.matcher(body);
		String title = "";
		while (titleMtc.find()) {
			// System.out.println("title: " + titleMtc.group(1));
			title = titleMtc.group(1).replace("\\\\u", "\\u");
		}
		// System.out.println(body);
		Pattern lptn = Pattern
				.compile("\"fmt_stream_map..:.(.*),...player_type");
		// "\"fmt_stream_map\\\": (.*),\\s+\\\"player_type\\\": \\\"html5\\\"");
		Matcher lmtc = lptn.matcher(body);
		if (lmtc.find()) {
			String jsonStr = lmtc.group(1)
					.replaceAll("([^\\\\])\\\\\"", "$1\"")
					.replaceAll("\\\\\\\"", "\"").replaceAll("\\\\\"", "\"")
					.replaceAll("\\\\\\/", "\\/").replaceAll("\\\\\\/", "\\/")
					.replaceAll("\\\\u", "\\u");
			// System.out.println(jsonStr);
			try {
				JSONArray jsonArr = new JSONArray(jsonStr);
				for (int i = 0; i < jsonArr.length(); i++) {
					JSONObject jsonObj = jsonArr.getJSONObject(i);
					result.add(new Info(title, jsonObj.getString("url"), "mp4"));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
