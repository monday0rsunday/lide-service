package com.broduce.lide.mobile;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class MNhacsoVideoCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		// System.out.println("link m nhac so:"+url);
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		String title = null;
		Pattern titlePtn = Pattern.compile("og:title\" content=\"([^\"|]+)");
		Matcher titleMtc = titlePtn.matcher(body);
		if (titleMtc.find()) {
			title = titleMtc.group(1);
			// System.out.println("title:"+title);
		}
		Pattern ptn = Pattern.compile("controls src=\"([^\"]+.mp4)");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			// System.out.println("data:"+mtc.group(1));
			try {
				result.add(new Info(title, URLDecoder.decode(mtc.group(1),
						"UTF-8"), "mp4"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
