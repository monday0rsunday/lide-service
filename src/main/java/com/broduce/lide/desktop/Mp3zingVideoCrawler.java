package com.broduce.lide.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class Mp3zingVideoCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		String title = null;
		Pattern titlePtn = Pattern
				.compile("<h1 class=\"detail-title\">([^<]+)");
		Matcher titleMtc = titlePtn.matcher(body);
		if (titleMtc.find()) {
			title = titleMtc.group(1);
		}
		Pattern ptn = Pattern
				.compile("flashvars.*(http://mp3.zing.vn/xml[^\\&]+)");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			String infoUrl = mtc.group(1);
			String infoBody = get(infoUrl + "?format=json");
			JSONArray jsonArr;
			try {
				jsonArr = new JSONObject(infoBody).getJSONObject("data")
						.getJSONArray("item");
				for (int j = 0; j < jsonArr.length(); j++) {
					JSONObject jsonObj = jsonArr.getJSONObject(j);
					for (int i = 0; i < jsonObj.getJSONArray("quality")
							.length(); i++) {
						result.add(new Info(title + " - f"
								+ jsonObj.getJSONArray("quality").getInt(i),
								jsonObj.getJSONArray("source").getString(i),
								"mp4"));
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
