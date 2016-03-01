var desktopHeaders = {
	"User-Agent": "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0"
}
var mobileHeaders = {
	"User-Agent": "Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"
}

var console = {
	log: function (blabla) {
		java.lang.System.out.println(JSON.stringify(blabla));
	}
}

var hm = {
}
function detect(url) {
	for (var p in hm) {
		if(hm[p]["regexp"].test(url)) {
			return hm[p].extractor(url);
		}
	}
	console.log("pattern not found for " + url);
	return [];
}




hm["tudou"] = {
	extractor: tudou_extractor,
	regexp: new RegExp("https?://(www\\.)?(m\\.)?tudou.com/.*view.*")
}
var iidRegex = /iid:[^0-9]*([0-9]+)/;
var titleRegex = /\<title>([^_<]+)/;
var mp4segsRegex = /mp4segs:[^0-9]*([0-9]+)/;
function tudou_extractor(url) {
	var infos = [];
	var html = dld.get(url, mobileHeaders);
	var iidRs = iidRegex.exec(html);
	var titleRs = titleRegex.exec(html);
	var mp4segsRs = mp4segsRegex.exec(html);
	var vTitle = "tudou-" + new Date().getTime();
	if (titleRs && titleRs.index > 0) {
		vTitle = titleRs[1];
	}
	if (iidRs && iidRs.index > 0 && mp4segsRs && mp4segsRs.index > 0){
		var vUrl = "http://vr.tudou.com/v2proxy/v2?it=" + iidRs[1] + "&st=" + mp4segsRs[1]+ "&pw="; 
		infos.push(new Info(vTitle, vUrl, "mp4"));
	}
	return infos;
}




hm["facebook1"] = {
	extractor: facebook_extractor,
	regexp: new RegExp("https?://(www\\.)?(m\\.)?facebook.com/.*/videos/.*")
}
hm["facebook2"] = {
	extractor: facebook_extractor,
	regexp: new RegExp("https?://(www\\.)?(m\\.)?facebook.com/.*video\\.php.*")
}
hm["facebook3"] = {
	extractor: facebook_extractor,
	regexp: new RegExp("https?://m\\.facebook.com/story.php\\?.*")
}
var fbRRegex = /story_fbid=([0-9]+)&id=([0-9]+)/
var fbBodyRegex = /(\[\["params".*\]\]).forEach/
function facebook_extractor(url) {
	var infos = [];
	var fbRRs = fbRRegex.exec(url);
	if(fbRRs && fbRRs.index > 0) {
		console.log("fuck");
		url = "https://www.facebook.com/" + fbRRs[2] + "/videos/"
					+ fbRRs[1] + "?_rdr=p";
	}
	var html = dld.get(url);
	var fbBodyRs = fbBodyRegex.exec(html);
	if (fbBodyRs && fbBodyRs.index > 0) {
		var fbBodyObj = JSON.parse(fbBodyRs[1]);
		fbBodyObj = JSON.parse(decodeURIComponent(fbBodyObj[0][1]));
		var title = "facebook-" + new Date().getTime();
		if (fbBodyObj["video_data_preference"]) {
			for(var key in fbBodyObj["video_data_preference"]) {
				var vArr = fbBodyObj["video_data_preference"][key];
				if (vArr) {
					for (var vIdx in vArr) {
						if (vArr[vIdx]["sd_src"]) {
							infos.push(new Info(title + "_sd", vArr[vIdx]["sd_src"], "mp4"));
						}
						if (vArr[vIdx]["hd_src"]) {
							infos.push(new Info(title + "_hd", vArr[vIdx]["hd_src"], "mp4"));
						}
						if (vArr[vIdx]["sd_src_no_ratelimit"]) {
							infos.push(new Info(title + "_sdnl", vArr[vIdx]["sd_src_no_ratelimit"], "mp4"));
						}
						if (vArr[vIdx]["hd_src_no_ratelimit"]) {
							infos.push(new Info(title + "_hdnl", vArr[vIdx]["hd_src_no_ratelimit"], "mp4"));
						}
					}
				}
			}
		}
	}
	return infos;
}
