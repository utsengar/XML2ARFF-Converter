package converter;

import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLSaxHandler extends DefaultHandler {
	private XMLValues xObj;
	ArrayList<XMLValues> xmlValueObj;

	public XMLSaxHandler() {
		xmlValueObj = new ArrayList<XMLValues>();
	}

	public ArrayList<XMLValues> getXMLObjArray(String filepath) {
		parseDocument(filepath);
		
		return xmlValueObj;
	}

	private void parseDocument(String filepath) {

		// get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {

			// get a new instance of parser
			SAXParser sp = spf.newSAXParser();

			// parse the file and also register this class for call backs
			sp.parse(filepath, this);

		} catch (SAXException se) {
			se.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attrs) throws SAXException {

		if (qName.equals("row")) {
			if (attrs.getValue("PostTypeId").equals("1")) {
				xObj = new XMLValues();
				xObj.setScore(Double.parseDouble(attrs.getValue("Score")));
				xObj.setViewCount(Double.parseDouble(attrs.getValue("ViewCount")));
				
				// xObj.setAnswerCount(Double.parseDouble(attrs.getValue("AnswerCount")));
				// xObj.setCommentCount(Double.parseDouble(attrs.getValue("CommentCount")));
				// xObj.setFavoriteCount(Double.parseDouble(attrs.getValue("FavoriteCount")));

				xObj.setTitle(attrs.getValue("Title"));
				xObj.setBody(attrs.getValue("Body"));
				xObj.setTags(attrs.getValue("Tags"));
				xObj.setDate(attrs.getValue("CreationDate"));
			}
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if(qName.equalsIgnoreCase("row")) {
			xmlValueObj.add(xObj);
		}

	}
}
