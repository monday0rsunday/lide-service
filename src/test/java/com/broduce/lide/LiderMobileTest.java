package com.broduce.lide;

import org.junit.Test;

import static org.junit.Assert.*;

public class LiderMobileTest {

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
	public void testMKeeng() {
		Lider lider = new Lider();
		assertFalse(lider
				.detect("http://m.keeng.vn/home.php/audio/Tha-Nguoi-Dung-Den-Vy-Thuy-Van-320Kbps/N7Y22F1J.html")
				.isEmpty());
		assertEquals(
				4,
				lider.detect(
						"http://m.keeng.vn/index.php/album/Anh-Oi-Nguyen-Hai-Yen/PUP32EER.html")
						.size());
		assertFalse(lider
				.detect("http://m.keeng.vn/index.php/video/Phia-Sau-Em-Kay-Tran/B4DS3SJY.html")
				.isEmpty());
	}

	@Test
	public void testMMp3zing() {
		Lider lider = new Lider();
		assertFalse(lider
				.detect("http://m.mp3.zing.vn/bai-hat/Vi-Toi-Con-Song-Tien-Tien/ZW7WFOU6.html")
				.isEmpty());
		assertFalse(lider
				.detect("http://m.mp3.zing.vn/album/Vo-Nguoi-Ta-Phan-Manh-Quynh/ZWZC0F0U.html")
				.isEmpty());
		assertEquals(
				3,
				lider.detect(
						"http://m.mp3.zing.vn/album/Trai-Dat-Tron-Khong-Gi-La-Khong-The-Single-Trung-Quan-Idol/ZWZCI60C.html")
						.size());
		assertFalse(lider
				.detect("http://m.mp3.zing.vn/video-clip/Rang-Em-Mai-O-Ben-Bich-Phuong/ZW7WFD9B.html")
				.isEmpty());
	}

	@Test
	public void testMNhaccuatui() {
		Lider lider = new Lider();
		assertFalse(lider
				.detect("http://m.nhaccuatui.com/bai-hat/am-tham-ben-em-son-tung-m-tp.Sttn5Z5WPKPR.html")
				.isEmpty());
		assertEquals(
				3,
				lider.detect(
						"http://m.nhaccuatui.com/playlist/het-roi-single-ho-quang-hieu.Z5yRgaH4njuT.html")
						.size());
		assertFalse(lider
				.detect("http://m.nhaccuatui.com/video/em-cua-nguoi-ta-trinh-thang-binh.iNwIEjd5pBVdE.html")
				.isEmpty());
	}

	@Test
	public void testMNhacso() {
		Lider lider = new Lider();
		assertFalse(lider.detect(
				"http://m.nhacso.net/nghe-nhac/bien-tinh.W11UVkpc.html")
				.isEmpty());
		assertEquals(
				30,
				lider.detect(
						"http://m.nhacso.net/nghe-playlist/cam-xuc.XFhRVEpZaA==.html")
						.size());
		assertEquals(
				9,
				lider.detect(
						"http://m.nhacso.net/nghe-album/hoai-niem-tinh-ca.XFhRUkFYbQ==.html")
						.size());
		assertFalse(lider
				.detect("http://m.nhacso.net/xem-video/soai-ca-super-hero-lyrics.XFhRUkZeaw==.html")
				.isEmpty());
	}

	@Test
	public void testMNhacvui() {
		Lider lider = new Lider();
		assertFalse(lider
				.detect("http://m.nhac.vui.vn/neu-em-con-ton-tai-trinh-dinh-quang-m538594c3p33587.html")
				.isEmpty());
		assertEquals(
				14,
				lider.detect(
						"http://m.nhac.vui.vn/album-sen-nhay-remix-khuu-huy-vu-a53882p6507.html")
						.size());
		assertFalse(lider
				.detect("http://m.nhac.vui.vn/lk-nhung-dieu-thay-chua-ke-ngoi-lai-ben-nhau-clip539126c32.html")
				.isEmpty());
	}

	@Test
	public void testMYoutube() {
		Lider lider = new Lider();
		assertEquals(5,
				lider.detect("https://m.youtube.com/watch?v=_qJwr3Fg_HQ")
						.size());
	}

}
