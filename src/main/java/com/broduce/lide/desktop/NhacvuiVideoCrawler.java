package com.broduce.lide.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class NhacvuiVideoCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		// System.out.println("link video nhac vui:"+url);
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		String title = null;
		Pattern titlePtn = Pattern.compile("<title>([^\"|]+)");
		Matcher titleMtc = titlePtn.matcher(body);
		if (titleMtc.find()) {
			title = titleMtc.group(1);
			// System.out.println("title:"+title);
		}
		Pattern ptn = Pattern.compile("fileAlt=([^&]+.mp4)");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			// System.out.println("data:"+mtc.group(0));
			result.add(new Info(title, mtc.group(1), "mp4"));
		}
		return result;
	}

}
