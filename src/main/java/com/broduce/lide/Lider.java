package com.broduce.lide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.broduce.lide.demo.AnimevnCrawler;
import com.broduce.lide.demo.ChiasenhacCrawler;
import com.broduce.lide.demo.DailymotionCrawler;
import com.broduce.lide.demo.Phim3sCrawler;
import com.broduce.lide.demo.PhimmoiCrawler;
import com.broduce.lide.demo.SoundcloundCrawler;
import com.broduce.lide.demo.VimeoCrawler;
import com.broduce.lide.demo.XemphimonCrawler;
import com.broduce.lide.desktop.FacebookCrawler;
import com.broduce.lide.desktop.KeengAlbumCrawler;
import com.broduce.lide.desktop.KeengBaihatCrawler;
import com.broduce.lide.desktop.KeengRadioCrawler;
import com.broduce.lide.desktop.KeengVideoCrawler;
import com.broduce.lide.desktop.Mp3zingAlbumCrawler;
import com.broduce.lide.desktop.Mp3zingCrawler;
import com.broduce.lide.desktop.Mp3zingVideoCrawler;
import com.broduce.lide.desktop.NhaccuatuiCrawler;
import com.broduce.lide.desktop.NhacsoAlbumCrawler;
import com.broduce.lide.desktop.NhacsoCrawler;
import com.broduce.lide.desktop.NhacsoVideoCrawler;
import com.broduce.lide.desktop.NhacvuiBaihatCrawler;
import com.broduce.lide.desktop.NhacvuiVideoCrawler;
import com.broduce.lide.desktop.TvzingCrawler;
import com.broduce.lide.desktop.TwitterCrawler;
import com.broduce.lide.desktop.YoutubeCrawler;
import com.broduce.lide.mobile.MKeengVideoCrawler;
import com.broduce.lide.mobile.MMp3zingAlbumCrawler;
import com.broduce.lide.mobile.MMp3zingVideoCrawler;
import com.broduce.lide.mobile.MNhaccuatuiCrawler;
import com.broduce.lide.mobile.MNhaccuatuiPlaylistCrawler;
import com.broduce.lide.mobile.MNhaccuatuiVideoCrawler;
import com.broduce.lide.mobile.MNhacsoCrawler;
import com.broduce.lide.mobile.MNhacsoPlaylistCrawler;
import com.broduce.lide.mobile.MNhacsoVideoCrawler;
import com.broduce.lide.mobile.MNhacvuiAlbumCrawler;
import com.broduce.lide.mobile.MNhacvuiBaihatCrawler;
import com.broduce.lide.mobile.MNhacvuiVideoCrawler;
import com.broduce.lide.model.Info;

public class Lider {
	private static Logger logger = Logger.getLogger(Lider.class);
	private HashMap<String, AbstractCrawler> hm = new HashMap<String, AbstractCrawler>();

	public Lider() {
		hm.put("https?://(www\\.)?(m\\.)?facebook.com/.*/videos/.*",
				new FacebookCrawler());
		hm.put("https?://(www\\.)?(mobile\\.)?twitter.com/.*/status/.*",
				new TwitterCrawler());
		hm.put("https?://m\\.facebook.com/story.php\\?.*",
				new FacebookCrawler());
		hm.put("https?://(www\\.)?(m\\.)?soundcloud.com/.*/.*",
				new SoundcloundCrawler());

		hm.put("https?://(www\\.)?(m\\.)?dailymotion.com/video/.*",
				new DailymotionCrawler());

		hm.put("https?://(www\\.)?(m\\.)?vimeo.com/[0-9]+", new VimeoCrawler());

		hm.put("https?://(www\\.)?(m\\.)?phimmoi.net/.*", new PhimmoiCrawler());

		hm.put("https?://(www\\.)?(m\\.)?phim3s.net/.*", new Phim3sCrawler());

		hm.put("https?://(www\\.)?(m\\.)?xemphimon.com/.*",
				new XemphimonCrawler());

		hm.put("https?://(www\\.)?(m\\.)?animevn.biz/.*", new AnimevnCrawler());

		// hm.put("https?://(www\\.)?(m\\.)?.*clip.vn/.*", new ClipvnCrawler());

		hm.put("https?://(www\\.)?youtube.com/watch\\?v=.*",
				new YoutubeCrawler());
		hm.put("https?://(www\\.)?m.youtube.com/watch\\?v=.*",
				new YoutubeCrawler());

		hm.put("https?://(www\\.)?mp3.zing.vn/bai-hat/.*", new Mp3zingCrawler());
		hm.put("https?://(www\\.)?mp3.zing.vn/video-clip/.*",
				new Mp3zingVideoCrawler());

		hm.put("https?://(www\\.)?m.mp3.zing.vn/video-clip/.*",
				new MMp3zingVideoCrawler());
		hm.put("https?://(www\\.)?mp3.zing.vn/album/.*",
				new Mp3zingAlbumCrawler());
		hm.put("https?://(www\\.)?m.mp3.zing.vn/(album|bai-hat)/.*",
				new MMp3zingAlbumCrawler());

		hm.put("https?://(www\\.)?nhacso.net/nghe-nhac/.*", new NhacsoCrawler());
		hm.put("https?://(www\\.)?nhacso.net/nghe-album/.*",
				new NhacsoAlbumCrawler());
		hm.put("https?://(www\\.)?nhacso.net/xem-video/.*",
				new NhacsoVideoCrawler());

		hm.put("https?://(www\\.)?m.nhacso.net/nghe-nhac/.*",
				new MNhacsoCrawler());
		hm.put("https?://(www\\.)?m.nhacso.net/nghe-playlist/.*",
				new MNhacsoPlaylistCrawler());
		hm.put("https?://(www\\.)?m.nhacso.net/nghe-album/.*",
				new MNhacsoPlaylistCrawler());
		hm.put("https?://(www\\.)?m.nhacso.net/xem-video/.*",
				new MNhacsoVideoCrawler());

		hm.put("https?://(www\\.)?nhaccuatui.com/.*", new NhaccuatuiCrawler());

		hm.put("https?://(www\\.)?m.nhaccuatui.com/playlist/.*",
				new MNhaccuatuiPlaylistCrawler());
		hm.put("https?://(www\\.)?m.nhaccuatui.com/bai-hat/.*",
				new MNhaccuatuiCrawler());
		hm.put("https?://(www\\.)?m.nhaccuatui.com/video/.*",
				new MNhaccuatuiVideoCrawler());

		hm.put("https?://(www\\.)?nhac.vui.vn/.*-clip.*",
				new NhacvuiVideoCrawler());
		hm.put("https?://(www\\.)?nhac.vui.vn/.*-m.*",
				new NhacvuiBaihatCrawler());
		hm.put("https?://(www\\.)?nhac.vui.vn/.*-a.*",
				new NhacvuiBaihatCrawler());

		hm.put("https?://(www\\.)?m.nhac.vui.vn/.*-clip.*",
				new MNhacvuiVideoCrawler());
		hm.put("https?://(www\\.)?m.nhac.vui.vn/.*-m.*",
				new MNhacvuiBaihatCrawler());
		hm.put("https?://(www\\.)?m.nhac.vui.vn/.*-a.*",
				new MNhacvuiAlbumCrawler());

		hm.put("https?://(www\\.)?keeng.vn/.*/video/.*",
				new KeengVideoCrawler());
		hm.put("https?://(www\\.)?keeng.vn/.*/audio/.*",
				new KeengBaihatCrawler());
		hm.put("https?://(www\\.)?keeng.vn/.*/radio/.*",
				new KeengRadioCrawler());
		hm.put("https?://(www\\.)?keeng.vn/.*/album/.*",
				new KeengAlbumCrawler());

		hm.put("https?://(www\\.)?m.keeng.vn/.*/video/.*",
				new MKeengVideoCrawler());
		hm.put("https?://(www\\.)?m.keeng.vn/.*/audio/.*",
				new KeengBaihatCrawler());
		hm.put("https?://(www\\.)?m.keeng.vn/.*/radio/.*",
				new KeengRadioCrawler());
		hm.put("https?://(www\\.)?m.keeng.vn/.*/album/.*",
				new KeengAlbumCrawler());

		hm.put("https?://(www\\.)?(playlist.)?chiasenhac.com.*",
				new ChiasenhacCrawler());

		hm.put("https?://(www\\.)?tv.zing.vn/video/.*", new TvzingCrawler());
	}

	public List<Info> detect(String url) {
		for (String pattern : hm.keySet()) {
			if (url.matches(pattern)) {
				List<Info> rs = hm.get(pattern).crawl(url);
				if (rs.isEmpty()) {
					throw new RuntimeException("nothing extracted");
				}
				return rs;
			}
		}
		logger.info("pattern not found for " + url);
		throw new RuntimeException("pattern not found");
	}

	public List<String> getSupportPatterns() {
		List<String> rs = new ArrayList<String>();
		rs.addAll(hm.keySet());
		for (int i = rs.size() - 1; i >= 0; i--) {
			if (rs.get(i).contains("youtube")) {
				rs.remove(i);
			}
		}
		return rs;
	}
}
