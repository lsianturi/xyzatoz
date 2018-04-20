package com.benclaus.koperasi.utility;

import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DomUtil {

	public static Element toDOM(String str, boolean normalize) throws Exception{
		Document doc = null;
		StringReader reader = new StringReader(str);
		InputSource xmlInp = new InputSource(reader);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder parser = docBuilderFactory.newDocumentBuilder();
		doc = parser.parse(xmlInp);
		Element root = doc.getDocumentElement();
		if (normalize) {
			root.normalize();
		}
		try {
			if (reader != null)
				reader.close();
		} catch (Exception ex) {
		}
		return root;
	}

	public static Element toDOM(InputStream is, boolean normalize) throws Exception {
		Document doc = null;
		InputSource xmlInp = new InputSource(is);

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder parser = docBuilderFactory.newDocumentBuilder();
		doc = parser.parse(xmlInp);
		Element root = doc.getDocumentElement();
		if (normalize) {
			root.normalize();
		}	
		try {
			if (is != null)
				is.close();
		} catch (Exception ex) {
		}
		return root;
	}

	public static Element toDOM(URL url) throws Exception{
		return toDOM(url.openStream());
	}

	public static Element toDOM(String str)throws Exception {
		return toDOM(str, true);
	}

	public static Element toDOM(InputStream is)throws Exception {
		return toDOM(is, true);
	}

	public static Element findTag(Element root, String tag) {
		if (root == null)
			return null;
		NodeList list = root.getElementsByTagName(tag);
		for (int loop = 0; loop < list.getLength(); loop++) {
			Node node = list.item(loop);
			if (node != null) {
				if (node instanceof Element) {
					Element element = ((Element) node);
					if (element.getTagName().equalsIgnoreCase(tag))
						return element;
				}
			}
		}
		return null;
	}

	public static String getText(Element root) {
		String text = getText(root, 0);
		return text;
	}

	public static String getText(Element root, int nth) {
		if (root == null)
			return null;
		String text = null;
		int numFound = -1;
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.TEXT_NODE) {
				numFound++;
				if (numFound == nth) {
					text = child.getNodeValue().trim();
					break;
				}
			}
		}
		return text;
	}

	public static Properties getParameter(Element root, String tag,
			String name, String value) {
		NodeList list = root.getElementsByTagName(tag);
		Properties props = new Properties();
		for (int loop = 0; loop < list.getLength(); loop++) {
			Node node = list.item(loop);
			if (node != null) {
				if (node instanceof Element) {
					Element element = ((Element) node);
					props.setProperty(element.getAttribute(name), element
							.getAttribute(value));
				}
			}
		}
		return props;
	}

}