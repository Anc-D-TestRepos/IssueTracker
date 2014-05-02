package controllers;


import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.EmployeeSearcher;
import beans.Employee;

/**
 * Servlet implementation class Authentification
 */
@WebServlet("/Authentification")
public class Authentification extends HttpServlet {
	private final String EMAIL = "emailAdress";
	private final String PASS = "pass"; 
	private final String EMPLOYEE = "employee";
	private final String ADMIN = "admin";
	private final String ADMIN_PAGE = "/AuthorizedAdmin";
	private final String USER_PAGE = "/AuthorizedUser";
	private final String ERROR_PAGE = "/ErrorPage";
	private final String PATH = "WEB-INF/resource/employeeData.xml";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentification() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	
	
	protected void performTask( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		
		InputStream stream = getServletContext().getResourceAsStream(PATH);
		HttpSession session = req.getSession(true);
		EmployeeSearcher employeeSearcher = new EmployeeSearcher(stream);
		Employee employee  = null;
		
		String email;
		String password;
		
		
		email = (String)req.getAttribute(EMAIL);
		password = (String)req.getAttribute(PASS);
		
		employee = employeeSearcher.findEmployee(email, password);
		
		if(employee!=null){
			
			session.setAttribute(EMPLOYEE, employee);
			
			if(ADMIN.equals(employee.getRole())){
			
				jump(ADMIN_PAGE, req, resp);
				
			}else{
				
				jump(USER_PAGE, req, resp);
			}
		}else{
			
			jump(ERROR_PAGE, req, resp);
		}
		
	}	
	
	

	
	protected void jump( String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		
		rd.forward(req, resp);
		
	}
	
}
