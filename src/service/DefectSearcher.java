package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import controllers.AuthorizedAdmin;
import beans.Defect;

public class DefectSearcher {
	private final String  FILE_ERR = "file defectData.xml not found";
	private final String  PARSE_ERR = "Invalid ID in defect list - id =  ";
	private final String  IO_ERR = "I\\O error in DefectSearcher - ";
	
	private Logger logger= Logger.getLogger(DefectSearcher.class);
	private InputStream stream;

	public DefectSearcher(InputStream stream) {
		super();
		this.stream = stream;
	
	}
	
	public List<Defect> findDefects() {
		DefectHandler defectHandler = new DefectHandler();
		
				
		try {
			if (stream!=null){
				
				XMLReader reader = XMLReaderFactory.createXMLReader();
				reader.setContentHandler(defectHandler);
					
				reader.parse(new InputSource(stream));
			}else{
				logger.error(FILE_ERR);
			}
				
				
		} catch ( IOException e) {
			

			logger.error(IO_ERR + e.getMessage());
		} catch(SAXException e){
			logger.error(PARSE_ERR + e.getMessage());
		}
		return defectHandler.getDefectsList();
	}
	
	
	
	public List<Defect> findDefects(int capacity) {
		
		DefectHandler defectHandler = new DefectHandler();
		List<Defect> result = null;
		
			try {
	
				if (stream!=null){
							
					XMLReader reader = XMLReaderFactory.createXMLReader();
					reader.setContentHandler(defectHandler);
					
					reader.parse(new InputSource(stream));
				}else{
					logger.error(FILE_ERR);
				}
				
			} catch ( IOException e) {
				
				logger.error(IO_ERR + e.getMessage());
			} catch(SAXException e){
				logger.error(PARSE_ERR + e.getMessage());
			}
			result = defectHandler.getDefectsList();
			
			if (result.size()>capacity){
				return result.subList(result.size()-capacity,result.size() );
			}else {
				return result;
			}
		
	}
	
	
	public List<Defect> findDefectsByUser(String email) {
		
		List<Defect> userDefects = new ArrayList<>();
		List<Defect> allDefects = findDefects();
		
		for (Defect defect : allDefects) {
			if (email.equals(defect.getAssignee())){
				userDefects.add(defect);
			}
		}
		
		return userDefects;
	}
	
	public List<Defect> findDefectsByUser(String email, int capacity) {
		
		List<Defect> userDefects = new ArrayList<>();
		List<Defect> allDefects = findDefects();
		
		for (Defect defect : allDefects) {
			if (email.equals(defect.getAssignee())){
				userDefects.add(defect);
			}
		}
		
		if (userDefects.size()>capacity){
			return userDefects.subList( userDefects.size()-capacity ,userDefects.size());
		}else {
			return userDefects;
		}
		
	}
}
