package com.broduce.lide.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class MNhaccuatuiPlaylistCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		List<Info> result = new ArrayList<Info>();
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Cookie",
				"DivisionAudioUserPhone=HTML5; DivisionVideoUserPhone=HTML5;");
		String body = get(url, headerMap);
		Pattern ptn = Pattern
				.compile("titles\\[[0-9]+\\]=\"([^\"]+)\";songs\\[[0-9]+\\]=\"([^\"]+mp[43])");
		Matcher mtc = ptn.matcher(body);
		while (mtc.find()) {
			result.add(new Info(mtc.group(1).replace("Nghe bài hát ", ""), mtc
					.group(2), "mp3"));
		}
		return result;
	}

}
