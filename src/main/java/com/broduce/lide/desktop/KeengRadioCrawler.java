package com.broduce.lide.desktop;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class KeengRadioCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		// System.out.println("keeng radio");
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		String title = null;
		Pattern titlePtn = Pattern.compile("<title>([^\"|]+)");
		Matcher titleMtc = titlePtn.matcher(body);
		if (titleMtc.find()) {
			title = titleMtc.group(1);
		}
		Pattern ptn = Pattern.compile("var albumPlayerXml = '([^']+)");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			String xmlRecords = get("http://www.keeng.vn" + mtc.group(1));
			// System.out.println("data:"+bd);

			DocumentBuilder db = null;
			try {
				db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlRecords));

			Document doc = null;
			try {
				doc = db.parse(is);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			NodeList nodes = doc.getElementsByTagName("track");

			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);

				NodeList name = element.getElementsByTagName("title");
				Element line0 = (Element) name.item(0);

				NodeList location = element.getElementsByTagName("location");
				Element line1 = (Element) location.item(0);

				result.add(new Info(title, getCharacterDataFromElement(line1),
						"mp3"));
			}
		}
		return result;
	}

	public String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

}
