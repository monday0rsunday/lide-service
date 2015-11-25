package com.broduce.lide.mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class MMp3zingAlbumCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		Pattern ptn = Pattern.compile("<div id=\"mp3Player\".*xml=\"([^\"]+)");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			String infoUrl = mtc.group(1);
			String infoBody = get(infoUrl + "?format=json");
			JSONArray jsonArr;
			try {
				jsonArr = new JSONObject(infoBody).getJSONArray("data");
				for (int i = 0; i < jsonArr.length(); i++) {

					result.add(new Info(jsonArr.getJSONObject(i).getString(
							"title"), jsonArr.getJSONObject(i).getString(
							"source"), "mp3"));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
