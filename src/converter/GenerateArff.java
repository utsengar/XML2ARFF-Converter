package converter;

import weka.core.Instances;

public class GenerateArff {
	public static void main(String[] args) throws Exception {

		String xmlFilePath = "/Users/utkarsh/Desktop/posts.sample.xml";
		XMLParser xmlObj = new XMLParser();
		XMLtoARFFConverter converterObj = new XMLtoARFFConverter();

		//You can select the no. of instances to be selected from XML
		Instances finalArffFile = converterObj.generateARFF(xmlObj.getXMLObjArray(xmlFilePath, 10));
		
		System.out.println(finalArffFile); //Print or save as file

	}
}
