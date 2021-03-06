package com.broduce.lide;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.broduce.lide.model.Info;

public abstract class AbstractCrawler {
	protected static PoolingHttpClientConnectionManager connectionManager;
	protected static CloseableHttpClient httpClient;

	private static final Logger logger = Logger
			.getLogger(AbstractCrawler.class);

	static {
		RequestConfig requestConfig = RequestConfig.custom()
				.setExpectContinueEnabled(false).setDecompressionEnabled(true)
				.setCookieSpec(CookieSpecs.DEFAULT).setRedirectsEnabled(true)
				.setSocketTimeout(10000).setConnectTimeout(100000).build();

		RegistryBuilder<ConnectionSocketFactory> connRegistryBuilder = RegistryBuilder
				.create();
		connRegistryBuilder.register("http",
				PlainConnectionSocketFactory.INSTANCE);
		try { // Fixing:
				// https://code.google.com/p/crawler4j/issues/detail?id=174
			// By always trusting the ssl certificate
			SSLContext sslContext = SSLContexts.custom()
					.loadTrustMaterial(null, new TrustStrategy() {
						@Override
						public boolean isTrusted(final X509Certificate[] chain,
								String authType) {
							return true;
						}
					}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext,
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			connRegistryBuilder.register("https", sslsf);
		} catch (Exception e) {
			logger.error("", e);
		}

		Registry<ConnectionSocketFactory> connRegistry = connRegistryBuilder
				.build();
		connectionManager = new PoolingHttpClientConnectionManager(connRegistry);
		connectionManager.setMaxTotal(100);
		connectionManager.setDefaultMaxPerRoute(10);

		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		clientBuilder.setDefaultRequestConfig(requestConfig);
		clientBuilder.setConnectionManager(connectionManager);
		clientBuilder.setUserAgent(UserAgent.getUserAgent(false));
		httpClient = clientBuilder.build();
	}

	private String pattern;

	public AbstractCrawler() {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

	public String get(String url) {
		StringBuffer body = new StringBuffer();
		HttpUriRequest req = new HttpGet(url);
		try {
			HttpResponse res = httpClient.execute(req);
			if (res.getStatusLine().getStatusCode() >= 200
					&& res.getStatusLine().getStatusCode() <= 299) {
				HttpEntity entity = res.getEntity();
				body.append(EntityUtils.toString(entity));
			} else {
				logger.error("status code of " + url + " is "
						+ res.getStatusLine().getStatusCode());
			}
		} catch (IOException e) {
			logger.error("", e);
		}
		return body.toString();
	}

	public String post(String url, Map<String, String> headers, String rbody) {
		StringBuffer body = new StringBuffer();
		HttpPost req = new HttpPost(url);
		for (String key : headers.keySet()) {
			req.setHeader(key, headers.get(key));
		}
		req.setEntity(new StringEntity(rbody, "UTF-8"));
		try {
			HttpResponse res = httpClient.execute(req);
			if (res.getStatusLine().getStatusCode() >= 200
					&& res.getStatusLine().getStatusCode() <= 299) {
				HttpEntity entity = res.getEntity();
				body.append(EntityUtils.toString(entity));
			} else {
				logger.error("status code of " + url + " is "
						+ res.getStatusLine().getStatusCode());
			}
		} catch (IOException e) {
			logger.error("", e);
		}
		return body.toString();
	}

	public String get(String url, Map<String, String> headers) {
		StringBuffer body = new StringBuffer();
		HttpGet req = new HttpGet(url);
		for (String key : headers.keySet()) {
			req.setHeader(key, headers.get(key));
		}
		try {
			HttpResponse res = httpClient.execute(req);
			if (res.getStatusLine().getStatusCode() >= 200
					&& res.getStatusLine().getStatusCode() <= 299) {
				HttpEntity entity = res.getEntity();
				body.append(EntityUtils.toString(entity));
			} else {
				logger.error("status code of " + url + " is "
						+ res.getStatusLine().getStatusCode());
			}
		} catch (IOException e) {
			logger.error("", e);
		}
		return body.toString();
	}

	public abstract List<Info> crawl(String url);

}
