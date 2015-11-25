package com.broduce.lide;

import org.junit.Test;
import static org.junit.Assert.*;

public class LiderDesktopMobileTest {

	@Test
	public void testChiasenhac() {
		Lider lider = new Lider();
		assertFalse(lider
				.detect("http://chiasenhac.com/mp3/vietnam/v-pop/sau-tim-thiep-hong~le-quyen-quang-le~1000824.html")
				.isEmpty());
		System.out
				.println(lider
						.detect("http://chiasenhac.com/mp3/us-uk/u-pop/hello~lionel-richie~1010978.html")
						.get(0));
		assertFalse(lider
				.detect("http://chiasenhac.com/mp3/us-uk/u-pop/hello~lionel-richie~1010978.html")
				.isEmpty());
		assertFalse(lider
				.detect("http://chiasenhac.com/nhac-hot-2/het-roi~ho-quang-hieu~1577673.html")
				.isEmpty());
		assertFalse(lider
				.detect("http://chiasenhac.com/nhac-hot-2/vo-nguoi-ta~phan-manh-quynh~1551980.html")
				.contains(
						"http://data2.chiasenhac.com/downloads/1552/0/1551980-d22d8d19/320/Vo%20Nguoi%20Ta%20-%20Phan%20Manh%20Quynh.mp3"));
	}

	@Test
	public void testSoundclound() {
		Lider lider = new Lider();
		assertFalse(lider.detect(
				"https://soundcloud.com/roywoodsofficial/drama-feat-drake")
				.isEmpty());
		assertFalse(lider.detect(
				"https://soundcloud.com/khamari-barnes/sorry-justin-bieber")
				.isEmpty());
		assertFalse(lider.detect(
				"https://m.soundcloud.com/khamari-barnes/sorry-justin-bieber")
				.isEmpty());
	}

	@Test
	public void testDailymotion() {
		Lider lider = new Lider();
		assertEquals(3, lider
				.detect("http://www.dailymotion.com/video/x2i2y2e").size());
	}

	@Test
	public void testVimeo() {
		Lider lider = new Lider();
		assertEquals(2, lider.detect("https://vimeo.com/109442889").size());
	}

	@Test
	public void testPhimmoi() {
		Lider lider = new Lider();
		assertEquals(
				3,
				lider.detect(
						"http://www.phimmoi.net/phim/cong-phu-gau-truc-ngay-le-dac-biet-3240/xem-phim.html")
						.size());
	}

	@Test
	public void testPhim3s() {
		Lider lider = new Lider();
		assertEquals(
				1,
				lider.detect(
						"http://phim3s.net/phim-le/to-chuc-bong-dem_8728/xem-phim/232800/")
						.size());
		assertEquals(
				1,
				lider.detect(
						"http://phim3s.net/phim-le/to-chuc-bong-dem_8728/xem-phim/232808/")
						.size());
	}

	@Test
	public void testXemphimon() {
		Lider lider = new Lider();
		assertEquals(
				1,
				lider.detect(
						"http://xemphimon.com/xem-phim-online/ky-uc-bo-quen/777684.html")
						.size());
		assertEquals(
				2,
				lider.detect(
						"http://xemphimon.com/xem-phim-online/phuong-an-b/494533.html")
						.size());
	}

	@Test
	public void testClipvn() {
		Lider lider = new Lider();
		assertEquals(
				1,
				lider.detect(
						"http://phim.clip.vn/watch/Bach-ma-hoang-tu-tap-12,RoP3/32101")
						.size());
		assertEquals(
				1,
				lider.detect(
						"http://clip.vn/watch/Tai-chinh-kinh-doanh-trua-20-11-2015,RojY/")
						.size());
		assertEquals(
				1,
				lider.detect(
						"http://clip.vn/watch/Choang-voi-ban-cover-Am-tham-ben-em-cua-hot-girl,RJgH/")
						.size());
	}

	@Test
	public void testAnimevn() {
		Lider lider = new Lider();
		assertEquals(
				2,
				lider.detect(
						"http://www.animevn.biz/xem-phim/k-return-of-kings-episode-1/15726.html")
						.size());
	}

}
