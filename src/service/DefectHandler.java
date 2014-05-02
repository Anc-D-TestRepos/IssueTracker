package service;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import beans.Defect;



public class DefectHandler  extends DefaultHandler {
	
	private final String NAME = "defect";
	private final int ID= 0;
	private final int PRIORITY = 1;
	private final int ASSIGNEE = 2;
	private final int TYPE = 3;
	private final int STATUS = 4;
	private final int SUMMARY = 5;
	private final String  PARSE_ERR = "Invalid ID in defect list - id =  ";

	private Defect defect = null;
	private List <Defect> defectsList= null;
	private Logger logger=null;

	
	
	
	public DefectHandler() {
		super();
		logger= Logger.getLogger(DefectHandler.class);
		defectsList = new ArrayList<>();
	}

	/**
	 * @return the employee
	 */
	public Defect getDefect() {
		return defect;
	}



	/**
	 * @return the defects
	 */
	public List<Defect> getDefectsList() {
		return defectsList;
	}

	public void startElement  (String uri, String  localName, 
			String  qName, Attributes  attributes )throws SAXException{


		if(NAME.equals(qName)){
	
			defect = new Defect();
			try {
				defect.setId(attributes.getValue(ID));
				defect.setPriority(attributes.getValue(PRIORITY));
				defect.setAssignee(attributes.getValue(ASSIGNEE));
				defect.setType(attributes.getValue(TYPE));
				defect.setStatus(attributes.getValue(STATUS));
				defect.setSummary(attributes.getValue(SUMMARY));
				
				defectsList.add(defect);
				
			} catch (ParseException e) {

				logger.error(PARSE_ERR + attributes.getValue(ID));
				defect=null; 
			}
		}
	}
}


