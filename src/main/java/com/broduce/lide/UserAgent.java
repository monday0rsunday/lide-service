package com.broduce.lide;

import java.util.Random;

public class UserAgent {

	private static final String[] deskAgents = new String[] { "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0" };
	private static final String[] mobiAgents = new String[] { "Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30" };
	private static final Random rand = new Random();

	public static String getUserAgent(boolean isMobi) {
		if (isMobi) {
			return deskAgents[rand.nextInt(mobiAgents.length)];
		} else {
			return deskAgents[rand.nextInt(deskAgents.length)];
		}
	}
}
