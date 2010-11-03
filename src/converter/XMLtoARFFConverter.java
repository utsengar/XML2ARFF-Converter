package converter;

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/** 914-631-9113
 * Generates ARFF file from XML
 * http://weka.wikispaces.com/Creating+an+ARFF+file
 * @author zengr
 */
public class XMLtoARFFConverter {
	
  public Instances generateARFF(ArrayList<XMLValues> xmlValues) throws Exception {
    FastVector      atts;
    Instances       data;
    double[]        vals;

    // 1. set up attributes
    atts = new FastVector();
    
    // - numeric
    atts.addElement(new Attribute("Score"));
    atts.addElement(new Attribute("ViewCount")); 
    atts.addElement(new Attribute("AnswerCount")); 
    atts.addElement(new Attribute("CommentCount")); 
    atts.addElement(new Attribute("FavoriteCount"));
    
    // - string
    atts.addElement(new Attribute("Title", (FastVector) null));
    atts.addElement(new Attribute("Body", (FastVector) null));
    atts.addElement(new Attribute("Tags", (FastVector) null));
    
    // - date
    atts.addElement(new Attribute("CreationDate", "yyyy-MM-dd"));

    // 2. create Instances object
    data = new Instances("Stackoverflow_Dump", atts, 0);

    // 3. fill with data
    for(int i=0; i<xmlValues.size(); i++)
    {
        vals = new double[data.numAttributes()];
        // - numeric
        vals[0] = xmlValues.get(i).getScore(); //Score
        vals[1] = xmlValues.get(i).getViewCount(); //ViewCount
        vals[2] = xmlValues.get(i).getAnswerCount(); //AnswerCount
        vals[3] = xmlValues.get(i).getCommentCount(); //CommentCount
        vals[4] = xmlValues.get(i).getFavoriteCount(); //FavoriteCount
        
        // - string
        vals[5] = data.attribute(5).addStringValue(xmlValues.get(i).getTitle()); //Title
        vals[6] = data.attribute(6).addStringValue(xmlValues.get(i).getBody()); //Body
        vals[7] = data.attribute(7).addStringValue(xmlValues.get(i).getTags()); //Tags
        
        // - date
        vals[8] = data.attribute(8).parseDate(xmlValues.get(i).getDate()); //CreationDate
        
        // add
        data.add(new Instance(1.0, vals));
    }
    
	return data;
  }
}