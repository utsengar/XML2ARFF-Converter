package converter;

import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLParser {
	
	public ArrayList<XMLValues> getXMLObjArray(String xmlFilePath, int instanceCount) throws ParserConfigurationException, SAXException, IOException
	{
		ArrayList<XMLValues> xmlValueObj = new ArrayList<XMLValues>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxHandler handler = new SaxHandler();
        
		for(int i=0; i<instanceCount; i++)
		{
	        // and parse
	        parser.parse(xmlFilePath, handler);
			xmlValueObj.add(handler.xObj);
		}
		
		return xmlValueObj;
	}
	
	
    private static final class SaxHandler extends DefaultHandler {
    	
		XMLValues xObj = new XMLValues();
        public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
    		
            if (qName.equals("row")) {
            	xObj.setScore(Double.parseDouble(attrs.getValue("Score")));
            	xObj.setViewCount(Double.parseDouble(attrs.getValue("ViewCount")));
            	xObj.setAnswerCount(Double.parseDouble(attrs.getValue("AnswerCount")));
            	xObj.setCommentCount(Double.parseDouble(attrs.getValue("CommentCount")));
            	xObj.setFavoriteCount(Double.parseDouble(attrs.getValue("FavoriteCount")));
            	
            	xObj.setTitle(attrs.getValue("Title"));
            	xObj.setBody(attrs.getValue("Body"));
            	xObj.setTags(attrs.getValue("Tags"));
            	xObj.setDate(attrs.getValue("Date"));
            	
            }
        }
    }
	
	
}
