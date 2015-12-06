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

}
