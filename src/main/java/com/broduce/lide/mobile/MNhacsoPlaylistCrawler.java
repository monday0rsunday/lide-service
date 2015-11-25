package com.broduce.lide.mobile;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.CharacterData;

import com.broduce.lide.AbstractCrawler;
import com.broduce.lide.model.Info;

public class MNhacsoPlaylistCrawler extends AbstractCrawler {

	@Override
	public List<Info> crawl(String url) {
		// System.out.println("link playlist nhac so:"+url);
		List<Info> result = new ArrayList<Info>();
		String body = get(url);
		Pattern ptn = Pattern.compile("file=([^&]+)");
		Matcher mtc = ptn.matcher(body);
		if (mtc.find()) {
			// System.out.println("data:"+mtc.group(1));
			String xmlRecords = get(mtc.group(1));

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
				// System.out.println("Title: " +
				// getCharacterDataFromElement(line));

				NodeList location = element.getElementsByTagName("location");
				Element line1 = (Element) location.item(0);
				// System.out.println("Location: " +
				// getCharacterDataFromElement(line));

				result.add(new Info(getCharacterDataFromElement(line0),
						getCharacterDataFromElement(line1), "mp3"));
			}
			// try {
			// result.add(new Info(title,
			// URLDecoder.decode(mtc.group(1),"UTF-8")));
			// } catch (UnsupportedEncodingException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
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
