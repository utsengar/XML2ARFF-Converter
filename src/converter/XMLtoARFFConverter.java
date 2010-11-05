package converter;

import weka.core.Instances;

public class XMLtoARFFConverter {
	public static void main(String[] args) throws Exception {

		String xmlFilePath = "/Users/utkarsh/Desktop/posts.sample.xml";
		XMLSaxHandler xmlObj = new XMLSaxHandler();
		ARFFGenerator converterObj = new ARFFGenerator();

		Instances finalArffFile = converterObj.generateARFF(xmlObj.getXMLObjArray(xmlFilePath));

		System.out.println(finalArffFile); // Print or save as file
	}
}
