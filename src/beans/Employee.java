package beans;

public class Employee {
	
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String password;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String firstName, String lastName, String email, String role,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result= result.append("First name: " + firstName + "\n");
		result= result.append("Last name : " + lastName + "\n");
		result= result.append("Email     : " + email + "\n");
		result= result.append("role      : " + role + "\n");
		result= result.append("password  : " + password);
			

		return result.toString();
	}

}
