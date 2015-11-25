package com.broduce.lide.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.UserAgent;
import com.broduce.lide.model.Info;

public class AnimevnCrawler extends AbstractCrawler {
	private Pattern titlePtn = Pattern.compile("(?m)title:\\s+\"([^\"]+)");
	private Pattern ptn = Pattern.compile("(?m)sources:\\s+(.+),$");

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url, UserAgent.getUserAgent(true), "");
		Matcher titleMtc = titlePtn.matcher(body);
		String title = null;
		if (titleMtc.find()) {
			title = titleMtc.group(1).trim();
		}
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			try {
				JSONArray jsonArr = new JSONArray(mtc.group(1));
				if (jsonArr.length() > 0) {
					for (int i = 0; i < jsonArr.length(); i++) {
						String link = jsonArr.getJSONObject(i)
								.getString("file");
						System.out.println(link);
						String type = jsonArr.getJSONObject(i)
								.getString("type");
						String label = jsonArr.getJSONObject(i).getString(
								"label");
						if (type.equals("video/mp4")
								&& label.matches("[0-9]+p")) {
							result.add(new Info(title + " " + label + (i + 1),
									link, "mp4"));
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
