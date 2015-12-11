package com.broduce.lide;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;

import com.broduce.lide.model.Info;

public abstract class AbstractCrawlerOld{

	private static final Logger logger = Logger
			.getLogger(AbstractCrawlerOld.class);

	private String pattern;

	public AbstractCrawlerOld() {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

	public String get(String url) {
		StringBuffer body = new StringBuffer();
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url)
					.openConnection();
			con.setRequestProperty("User-Agent", UserAgent.getUserAgent(false));
			con.connect();
			for (String key : con.getHeaderFields().keySet()) {
				for (String v : con.getHeaderFields().get(key)) {
					// System.out.println(key + "=" + v);
				}
			}
			boolean unzip = false;
			if (con.getHeaderFields().containsKey("Content-Encoding")
					&& con.getHeaderFields().get("Content-Encoding")
							.contains("gzip"))
				unzip = true;
			BufferedReader br;
			if (!unzip)
				br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
			else
				br = new BufferedReader(new InputStreamReader(
						new GZIPInputStream(con.getInputStream())));
			char[] buffer = new char[1024];
			int n = -1;
			while ((n = br.read(buffer)) > 0) {
				body.append(buffer, 0, n);
			}
			br.close();
			con.disconnect();
		} catch (Exception e) {
			logger.warn("", e);
		}
		return body.toString();
	}

	public String post(String url, String rbody) {
		StringBuffer body = new StringBuffer();
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url)
					.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", UserAgent.getUserAgent(false));
			DataOutputStream writer = new DataOutputStream(
					con.getOutputStream());
			writer.writeBytes(rbody);
			writer.flush();
			writer.close();
			for (String key : con.getHeaderFields().keySet()) {
				for (String v : con.getHeaderFields().get(key)) {
					// System.out.println(key + "=" + v);
				}
			}
			boolean unzip = false;
			if (con.getHeaderFields().containsKey("Content-Encoding")
					&& con.getHeaderFields().get("Content-Encoding")
							.contains("gzip"))
				unzip = true;
			BufferedReader br;
			if (!unzip)
				br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
			else
				br = new BufferedReader(new InputStreamReader(
						new GZIPInputStream(con.getInputStream())));
			char[] buffer = new char[1024];
			int n = -1;
			while ((n = br.read(buffer)) > 0) {
				body.append(buffer, 0, n);
			}
			br.close();
			con.disconnect();
		} catch (Exception e) {
			logger.warn("", e);
		}
		return body.toString();
	}

	public String get(String url, String cookie) {
		StringBuffer body = new StringBuffer();
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url)
					.openConnection();
			con.setRequestProperty("User-Agent", UserAgent.getUserAgent(false));
			con.setRequestProperty("Cookie", cookie);
			con.connect();
			for (String key : con.getHeaderFields().keySet()) {
				for (String v : con.getHeaderFields().get(key)) {
					// System.out.println(key + "=" + v);
				}
			}
			boolean unzip = false;
			if (con.getHeaderFields().containsKey("Content-Encoding")
					&& con.getHeaderFields().get("Content-Encoding")
							.contains("gzip"))
				unzip = true;
			BufferedReader br;
			if (!unzip)
				br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
			else
				br = new BufferedReader(new InputStreamReader(
						new GZIPInputStream(con.getInputStream())));
			char[] buffer = new char[1024];
			int n = -1;
			while ((n = br.read(buffer)) > 0) {
				body.append(buffer, 0, n);
			}
			br.close();
			con.disconnect();
		} catch (Exception e) {
			logger.warn("", e);
		}
		return body.toString();
	}

	public String get(String url, String useragent, String cookie) {
		StringBuffer body = new StringBuffer();
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url)
					.openConnection();
			con.setRequestProperty("User-Agent", useragent);
			con.setRequestProperty("Cookie", cookie);
			con.connect();
			for (String key : con.getHeaderFields().keySet()) {
				for (String v : con.getHeaderFields().get(key)) {
					// System.out.println(key + "=" + v);
				}
			}
			boolean unzip = false;
			if (con.getHeaderFields().containsKey("Content-Encoding")
					&& con.getHeaderFields().get("Content-Encoding")
							.contains("gzip"))
				unzip = true;
			BufferedReader br;
			if (!unzip)
				br = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
			else
				br = new BufferedReader(new InputStreamReader(
						new GZIPInputStream(con.getInputStream())));
			char[] buffer = new char[1024];
			int n = -1;
			while ((n = br.read(buffer)) > 0) {
				body.append(buffer, 0, n);
			}
			br.close();
			con.disconnect();
		} catch (Exception e) {
			logger.warn("", e);
		}
		return body.toString();
	}

	public abstract List<Info> crawl(String url);

}
