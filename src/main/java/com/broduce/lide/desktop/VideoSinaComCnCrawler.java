package com.broduce.lide.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class VideoSinaComCnCrawler extends AbstractCrawler {

	Pattern i1Ptn = Pattern.compile("video_id:'([0-9]+)");
	Pattern i2Ptn = Pattern.compile(".*#([0-9]+).*");

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		String id = null;
		String callback = "HTML5_" + System.currentTimeMillis();
		if (url.contains("/view")) {
			String body = get(url);
			Matcher iMtc = i1Ptn.matcher(body);
			if (iMtc.find()) {
				id = iMtc.group(1);
			}
		} else if (url.contains("news") || url.contains("ent")) {
			Matcher iMtc = i2Ptn.matcher(url);
			if (iMtc.find()) {
				id = iMtc.group(1);
			}
		}
		if (id != null) {
			String callbackUrl = "http://s.video.sina.com.cn/video/h5play?video_id="
					+ id + "&callback=" + callback;
			String jsonStr = get(callbackUrl);
			jsonStr = jsonStr.replace(callback + "(", "");
			jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
			JSONObject jsonObj = new JSONObject(jsonStr);
			if (!jsonObj.isNull("data")) {
				JSONObject dataObj = jsonObj.getJSONObject("data");
				String title = dataObj.getString("title");
				for (Object key : dataObj.getJSONObject("videos").keySet()) {
					JSONObject svObj = dataObj.getJSONObject("videos")
							.getJSONObject(key.toString());
					for (Object vkey : svObj.keySet()) {
						JSONObject vObj = svObj.getJSONObject(vkey.toString());
						result.add(new Info(title + vkey.toString(), vObj
								.getString("file_api")
								+ "?vid="
								+ vObj.getString("file_id"), vObj
								.getString("type")));
					}
				}
			}
		}
		return result;
	}
}
