package service;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import beans.Employee;



public class EmployeeHandler extends DefaultHandler {
	private final String PASS = "password";
	private final String EMAIL = "email"; 
	private final String NAME = "user-data";
	private final int FIRST_NAME = 0;
	private final int LAST_NAME = 1;
	private final int ROLE = 2;
	private String email;
	private String password;
	private String parseEmail;
	private String parsePassword;
	private Employee employee = null;
	private String result;
	
	
	
	public EmployeeHandler() {
		super();
		
	}

	
	
	public EmployeeHandler(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		
	}



	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}


	public void startElement  (String uri, String  localName, 
			String  qName, Attributes  attributes )throws SAXException {

		if(NAME.equals(qName)){
			
			if((email.equals(parseEmail))&(password.equals(parsePassword))){
				employee = new Employee();
				employee.setFirstName(attributes.getValue(FIRST_NAME));
				employee.setLastName(attributes.getValue(LAST_NAME));
				employee.setRole(attributes.getValue(ROLE));
				employee.setEmail(email);
				employee.setPassword(password);
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		 result = new String(ch,start,length).trim();
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if(!result.isEmpty()){
			if(EMAIL.equals(qName)){
				parseEmail = result;
			
			}
			if(PASS.equals(qName)){
				parsePassword = result;
				
			}
		}
	}

}
