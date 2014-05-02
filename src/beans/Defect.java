package beans;

import java.text.ParseException;

public class Defect {
	
	private int id ;
	private String priority;
	private String assignee;
	private String type;
	private String status;
	private String summary;
	
	
	/**
	 * 
	 */
	public Defect() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param id
	 * @param priority
	 * @param assignee
	 * @param type
	 * @param status
	 * @param summary
	 */
	public Defect(int id, String priority, String assignee, String type,
			String status, String summary) {
		super();
		this.id = id;
		this.priority = priority;
		this.assignee = assignee;
		this.type = type;
		this.status = status;
		this.summary = summary;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public void setId(String id) throws ParseException {
		this.id = Integer.parseInt(id);
	}
	
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the assignee
	 */
	public String getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/** 
	 * 
	 */
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		result= result.append("ID       : " + id + "\n");
		result= result.append("Priority : " + priority + "\n");
		result= result.append("Assignee : " + assignee + "\n");
		result= result.append("Type     : " + type + "\n");
		result= result.append("Status   : " + status+ "\n");
		result= result.append("Summary  : " + summary);
			

		return result.toString();
	}
	
	
	

}
