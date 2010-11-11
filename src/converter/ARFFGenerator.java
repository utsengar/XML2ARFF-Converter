package converter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 * 914-631-9113 Generates ARFF file from XML
 * http://weka.wikispaces.com/Creating+an+ARFF+file
 * 
 * @author zengr
 */
public class ARFFGenerator {
	ARFFGenerator(){
		
	}

	public Instances generateARFF(ArrayList<XMLValues> xmlValues)
			throws Exception {
		FastVector atts;
		Instances data;
		double[] vals;

		// 1. set up attributes
		atts = new FastVector();

		// - numeric
		atts.addElement(new Attribute("Score"));
		atts.addElement(new Attribute("ViewCount"));

		// - string
		atts.addElement(new Attribute("Tags", (FastVector) null));

		// - date
		atts.addElement(new Attribute("CreationDate", "yyyy-MM-dd"));

		// 2. create Instances object
		data = new Instances("Stackoverflow_Dump", atts, 0);

		try
		{
		// 3. fill with data
		for (int i = 0; i < xmlValues.size(); i++) {
			vals = new double[data.numAttributes()];
			// - numeric
			vals[0] = xmlValues.get(i).getScore(); // Score
			vals[1] = xmlValues.get(i).getViewCount(); // ViewCount

			// - string
			vals[2] = data.attribute(2).addStringValue(xmlValues.get(i).getTags()); // Tags

			// - date
			vals[3] = data.attribute(3).parseDate(xmlValues.get(i).getDate()); // CreationDate

			// add
			data.add(new Instance(1.0, vals));
		}
		}
		catch(Exception ex)
		{
			System.out.println("Error: " + ex.toString());
		}

		// Create file
		FileWriter fstream = new FileWriter("Output.arff", true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(data.toString());

		// Close the output stream
		out.close();

		return data;
	}
}