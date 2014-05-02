package service;

import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import beans.Employee;

public class EmployeeSearcher {
	
	private final String  FILE_ERR = "file employeeData.xml not found";
	private final String  PARSE_ERR = "Invalid ID in defect list - id =  ";
	private final String  IO_ERR = "I\\O error in AuthorizedAdmin - ";
	private InputStream stream;
	private Logger logger= Logger.getLogger(DefectSearcher.class);

	
	
	public EmployeeSearcher(InputStream stream) {
		super();
		
		this.stream = stream;
	}

	
	public Employee findEmployee(String email, String password) {
			EmployeeHandler employeehandler = new EmployeeHandler(email, password);
		
			try {
				if (stream!=null){
				
					XMLReader reader = XMLReaderFactory.createXMLReader();
					reader.setContentHandler(employeehandler);
					reader.parse(new InputSource(stream));
				}else{
					logger.error(FILE_ERR);
				}
			} catch ( IOException e) {
				logger.error(IO_ERR + e.getMessage());
			} catch(SAXException e){
				logger.error(PARSE_ERR + e.getMessage());
			}
		return employeehandler.getEmployee();
	}
}
