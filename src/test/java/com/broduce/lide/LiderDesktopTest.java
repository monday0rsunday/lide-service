package com.broduce.lide;

import org.junit.Test;
import static org.junit.Assert.*;

public class LiderDesktopTest {

	@Test
	public void testChiasenhac() {
		Lider lider = new Lider();
		assertFalse(lider
				.detect("http://chiasenhac.com/mp3/vietnam/v-pop/sau-tim-thiep-hong~le-quyen-quang-le~1000824.html")
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
	public void testKeeng() {
		Lider lider = new Lider();
		assertFalse(lider
				.detect("http://www.keeng.vn/index.php/audio/Tinh-Da-Ngu-Say-Bang-Cuong-320Kbps/H7JK9KYT.html")
				.isEmpty());
		assertEquals(
				13,
				lider.detect(
						"http://www.keeng.vn/index.php/album/Nhung-Bai-Hat-Hay-Nhat-Cua-Hai-Bang-Hai-Bang/NWTDCKWQ.html")
						.size());
		assertFalse(lider
				.detect("http://www.keeng.vn/index.php/video/Phia-Sau-Em-Kay-Tran/B4DS3SJY.html")
				.isEmpty());
	}

	@Test
	public void testTvzing() {
		Lider lider = new Lider();
		assertEquals(
				2,
				lider.detect(
						"http://tv.zing.vn/video/Hay-Nham-Mat-Khi-Anh-Den-Tap-14/IWZBB9U6.html")
						.size());
	}

	@Test
	public void testFacebook() {
		Lider lider = new Lider();
		assertEquals(
				4,
				lider.detect(
						"https://www.facebook.com/minhcuong/videos/10153480402439495/")
						.size());
		assertEquals(
				4,
				lider.detect(
						"http://facebook.com/647059494/videos/10153480402439495")
						.size());
		assertEquals(
				4,
				lider.detect(
						"https://m.facebook.com/story.php?story_fbid=10153480402439495&id=647059494&refid=17&_ft_=top_level_post_id.10153480402439495%3Atl_objid.10153480402439495%3Athid.647059494%3A306061129499414%3A54%3A0%3A1451635199%3A3269890946520818912&__tn__=C")
						.size());
	}

	@Test
	public void testTwitter() {
		Lider lider = new Lider();
		assertEquals(
				1,
				lider.detect(
						"https://twitter.com/guardian_sport/status/680697101072764928")
						.size());
		assertEquals(
				1,
				lider.detect(
						"https://mobile.twitter.com/guardian_sport/status/680697101072764928")
						.size());
	}

	@Test
	public void testVideoSinaComCn() {
		Lider lider = new Lider();
		assertEquals(4,
				lider.detect("http://video.sina.com.cn/view/250342743.html")
						.size());
		assertEquals(
				4,
				lider.detect(
						"http://video.sina.com.cn/news/spj/topvideoes20151222/#250378863")
						.size());
		assertEquals(4, lider.detect("http://video.sina.com.cn/ent/#250386117")
				.size());
	}

}
