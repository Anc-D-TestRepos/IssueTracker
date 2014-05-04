package controllers;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import service.DefectSearcher;
import beans.Defect;
import beans.Employee;

/**
 * Servlet implementation class AuthorizedUser
 */
@WebServlet("/AuthorizedUser")
public class AuthorizedUser extends HttpServlet {
	private final String  HAS_DEFECT = "hasDefect";
	private final String EMPLOYEE = "employee";
	private final String ADRESS = "emailAdress";
	private final String  IO_ERR = "I\\O error in AuthorizedUser - ";
	private final String LOG_ATRR = "log4";
	private Logger logger ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorizedUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request,response);
	}
	
	protected void performTask(HttpServletRequest req, HttpServletResponse res) {
		int capacity = 10;
		logger = (Logger)getServletContext().getAttribute(LOG_ATRR);
		HttpSession session = req.getSession();
		DefectSearcher defectSearch = new DefectSearcher(getServletContext().getResourceAsStream("WEB-INF/resource/defectData.xml"));
		boolean hasDefect = (boolean)session.getAttribute(HAS_DEFECT);
		Employee employee = (Employee) session.getAttribute(EMPLOYEE);
				
		String email = req.getParameter(ADRESS);
		List<Defect> defectList = defectSearch.findDefectsByUser(email, capacity);
		

		res.setContentType("text/html; charset=utf-8");
		
		PrintWriter out;
		try {
			out = res.getWriter();
			out.print("<!DOCTYPE html>");
			out.print("<html lang=\"en\"><head>");
			out.print(" <meta charset=\"UTF-8\"> <title>Issue Tracker</title>");
			out.print("<style type=\"text/css\">");
			out.print("body {font-size: 20px;}"
						+"div.user {font-size: 15px;margin: 0 0 0 10px;}"
						+"span.name{font-size: 20px;font-weight: 500;margin: 0 0 0 10px;}"
						+"span.logout{font-size: 15px;font-weight: 500;margin: 0 0 0 10px;}"
						+"div.button {display: table-cell;text-align: left;	padding: 0 25px 0 0;}"
						+".defectList table {	border: solid 1px;border-spacing: 0px;width: 100%;margin: 40px 0 0 0 ;}"
						+".defectList table td,th {border-collapse: collapse;height: 25px;border: solid 1px;}");
			out.print("</style></head>");
			out.print("<body>");
			out.print("<div class=\"user\">User:<span class=\"name\">"+employee.getFirstName()+"  "+employee.getLastName()+"</span>"
						+"<span class=\"logout\"><a href=\"LogoutController\">logout</a></span></div>");
			out.print("<div class=\"chngUserData\">");
			out.print("</div>");
			out.print("<hr color=\"red\">");
			out.print("<div class=\"button\">");
			out.print("<a href=\"CreateDefectController\"><input type=\"button\" value=\"submit issue\"></a>");
			out.print("<a href=\"SearchController\"><input type=\"button\" value=\"search\"></a>");
			out.print("</div>");
			if ((defectList!=null) & (!defectList.isEmpty())){
				out.print("<div class=\"defectList\">");
				out.print("<table><tr>");
				out.print(	"<th><a href=\"\">Id</a></th>"
						+"<th><a href=\"\">Priority</a></th>"
						+"<th><a href=\"\">Assignee</a></th>"
						+"<th><a href=\"\">Type</a></th>"
						+"<th><a href=\"\">Status</a></th>"
						+"<th><a href=\"\">Summary</a></th>");
				out.print("</tr>");
			
				for( int row = 0; row <= defectList.size() - 1; row++){
					out.print("<tr>");
					out.print("<td>" + defectList.get(row).getId() + "</td>");
					out.print("<td>" + defectList.get(row).getPriority() + "</td>");
					out.print("<td>" + defectList.get(row).getAssignee() + "</td>");
					out.print("<td>" + defectList.get(row).getType() + "</td>");
					out.print("<td>"  +defectList.get(row).getStatus() + "</td>");
					out.print("<td>" + defectList.get(row).getSummary() + "</td>");
					out.print("</tr>");
				}
				
			
				out.print("</table></div>");
			}else{
				if(hasDefect){
					out.print("<p align=\"center\">No found defects assigned for you</p>");
				}else{
					out.print("<p align=\"center\">Application doesnt have any defects </p>");
				}
			}
			out.print( "</body></html>");
		
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error(IO_ERR + e.getMessage());
		}
	}
}
