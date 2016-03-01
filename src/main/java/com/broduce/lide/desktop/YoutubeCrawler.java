package com.broduce.lide.desktop;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.UserAgent;
import com.broduce.lide.model.Info;

public class YoutubeCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:43.0) Gecko/20100101 Firefox/43.0");
		String body = get(url.replace("m.youtube", "www.youtube"));
		Pattern titlePtn = Pattern.compile("<title>([^<]+) - YouTube</title>");
		Matcher titleMtc = titlePtn.matcher(body);
		String title = "";
		if (titleMtc.find()) {
			title = titleMtc.group(1);
		}
		Pattern lptn = Pattern.compile("ytplayer.config = (.*);ytplayer.load");
		Matcher lmtc = lptn.matcher(body);
		if (lmtc.find()) {
			String jsonStr = lmtc.group(1);
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);
				String videos[] = jsonObj.getJSONObject("args")
						.getString("url_encoded_fmt_stream_map").split(",");
				for (String video : videos) {
					String infos[] = video.split("&");
					String vTitle = title.replaceAll("&#[0-9]+;", "");
					String link = null;
					String type = "mp4";
					for (String info : infos) {
						if (info.startsWith("quality=")
								|| info.startsWith("type=")) {
						}
						if (info.startsWith("type=video%2F")) {
							if (info.contains("%3B")) {
								type = info.substring(13, info.indexOf("%3B"))
										.replace("x-flv", "flv");
							} else {
								type = info.substring(13).replace("x-flv",
										"flv");
							}
						}
						if (info.startsWith("url=")) {
							link = URLDecoder.decode(info.substring(4));
						}
					}
					result.add(new Info(vTitle, link, type));
				}
			} catch (JSONException e) {
				Logger.getLogger(getClass()).warn("", e);
			}
		}
		return result;
	}
}
