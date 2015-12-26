package com.broduce.lide.desktop;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class FacebookCrawler extends AbstractCrawler {

	Pattern rPtn = Pattern.compile("story_fbid=([0-9]+)&id=([0-9]+)");

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		Matcher rMtc = rPtn.matcher(url);
		if (rMtc.find()) {
			url = "https://www.facebook.com/" + rMtc.group(2) + "/videos/"
					+ rMtc.group(1) + "?_rdr=p";
		}
		String body = get(url);
		Pattern ptn = Pattern.compile("(\\[\\[\"params\".*]])\\.forEach");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			JSONArray bodyObj = new JSONArray(mtc.group(1));
			JSONObject jsonObj = new JSONObject(URLDecoder.decode(bodyObj
					.getJSONArray(0).getString(1)));
			JSONObject vpObj = jsonObj.getJSONObject("video_data_preference");
			for (Object key : vpObj.keySet()) {
				if (!vpObj.isNull(key.toString())) {
					JSONArray vpArr = vpObj.getJSONArray(key.toString());
					for (int i = 0; i < vpArr.length(); i++) {
						JSONObject vObj = vpArr.getJSONObject(i);
						String title = "facebook";
						if (vObj.has("video_id"))
							title = vObj.getString("video_id");
						if (vObj.has("sd_src"))
							result.add(new Info(title + "_sd", vObj
									.getString("sd_src"), "mp4"));
						if (vObj.has("hd_src"))
							result.add(new Info(title + "_hd", vObj
									.getString("hd_src"), "mp4"));
						if (vObj.has("sd_src_no_ratelimit"))
							result.add(new Info(title + "_sdnl", vObj
									.getString("sd_src_no_ratelimit"), "mp4"));
						if (vObj.has("hd_src_no_ratelimit"))
							result.add(new Info(title + "_hdnl", vObj
									.getString("hd_src_no_ratelimit"), "mp4"));
					}
				}
			}
		}
		return result;
	}
}