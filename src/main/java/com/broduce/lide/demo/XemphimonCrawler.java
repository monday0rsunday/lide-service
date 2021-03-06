package com.broduce.lide.demo;

import java.net.URLEncoder;
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
import com.broduce.lide.model.Info;

public class XemphimonCrawler extends AbstractCrawler {
	private Pattern titlePtn = Pattern.compile("<title>Xem Phim ([^\"|<~]+)");
	private Pattern ptn = Pattern.compile("loadLink\\(\"([^\"]+)");

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
				Map<String, String> headerMap = new HashMap<String, String>();
				headerMap.put("Content-Type",
						"application/x-www-form-urlencoded");
				String jsBody = post(
						"http://play.xemphimon.com/proxy/proxy.php", headerMap,
						"file=" + URLEncoder.encode(mtc.group(1)));
				JSONObject jsonObj = new JSONObject(jsBody);
				JSONArray jsonArr = jsonObj.getJSONArray("result");
				for (int i = 0; i < jsonArr.length(); i++) {
					JSONObject itemObj = jsonArr.getJSONObject(i);
					result.add(new Info(
							title + " " + itemObj.getString("name"), itemObj
									.getString("link"), "mp4"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).warn("", e);
			}
		}
		return result;
	}
}
