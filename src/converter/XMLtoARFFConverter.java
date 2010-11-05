package converter;

public class XMLtoARFFConverter {
	public static void main(String[] args) throws Exception {
		
		String xmlFilePath = "/Users/utkarsh/Desktop/Datasets/Stack Overflow Data Dump - Oct 2010/Content/Export-100110/102010 Stack Overflow/posts.xml";
		XMLSaxHandler xmlObj = new XMLSaxHandler();
		xmlObj.generateArff(xmlFilePath);
	}
}
