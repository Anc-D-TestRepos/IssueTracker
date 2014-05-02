package controllers;

import java.io.File;
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
 * Servlet implementation class AuthorizedAdmin
 */
@WebServlet("/AuthorizedAdmin")
public class AuthorizedAdmin extends HttpServlet {
	private final String  HAS_DEFECT = "hasDefect";
	private final String EMPLOYEE = "employee";
	private final String ADRESS = "emailAdress";
	private final String  IO_ERR = "I\\O error in AuthorizedAdmin - ";
	private final String PATH = "WEB-INF/resource/defectData.xml";
	private final String LOG_ATRR = "log4";
	private Logger logger ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorizedAdmin() {
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
		HttpSession session = req.getSession();
		logger = (Logger)getServletContext().getAttribute(LOG_ATRR);
		
		DefectSearcher defectSearch = new DefectSearcher(getServletContext().getResourceAsStream(PATH));
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
						+"div.user span{font-size: 20px;font-weight: 400;margin: 0 0 0 10px;}"
						+"span.chngData,span.chngPass {font-size: 15px;}"
						+"span.chngData {margin: 10px 0 0 10px;}"
						+"span.chngPass {margin: 0 0 0 10px;}"
						+"div.button {display: table-cell;text-align: left;	padding: 0 25px 0 0;}"
						+"div.links a{float: right;margin: 0 20px;}"
						+"div.addLinks a{float: left;margin: 15px 20px  0 20px;}"
						+"div.userAction a{float: right;float: top;margin: 15px 20px  0 20px;}"
						+".defectList table {	border: solid 1px;border-spacing: 0px;width: 100%;margin: 40px 0 0 0 ;}"
						+".defectList table td,th {border-collapse: collapse;height: 25px;border: solid 1px;}");
						
			out.print("</style></head>");
			out.print("<body>");
			out.print("<div class=\"user\">Admin: <span>"+employee.getFirstName()+"  "+employee.getLastName()+"</span></div>");
			out.print("<div class=\"chngUserData\">");
			out.print("<span class=\"chngData\"><a href=\"\">change user data</a></span>");
			out.print("<span class=\"chngPass\"><a href=\"\">change password</a></span>");
			out.print("</div>");
			out.print("<hr color=\"red\">");
			out.print("<div class=\"button\">");
			out.print("<input type=\"button\" value=\"submit issue\"  action=\"CreateDefectController\">");
			out.print("<input type=\"button\" value=\"search\"  action=\"SearchController\">");
			out.print("</div>");
			out.print("<div class=\"links\">");
			out.print("<a href=\"\">Projects </a>");
			out.print("<a href=\"\">Statuses </a>");
			out.print("<a href=\"\">Resolutions </a>");
			out.print("<a href=\"\">Priorities </a>");
			out.print("<a href=\"\">Types </a>");
			out.print(" </div>");
			
			if ((defectList!=null) & (!defectList.isEmpty())){
				out.print("<div class=\"defectList\">");
				out.print("<table><tr>");
				out.print("<th><a href=\"\">Id</a></th>"
						+"<th><a href=\"\">Priority</a></th>"
						+"<th><a href=\"\">Assignee</a></th>"
						+"<th><a href=\"\">Type</a></th>"
						+"<th><a href=\"\">Status</a></th>"
						+"<th><a href=\"\">Summary</a></th>");
				out.print("</tr>");
			
				for( int row=0;row<=defectList.size()-1;row++){
					out.print("<tr>");
					out.print("<td>"+defectList.get(row).getId()+"</td>");
					out.print("<td>"+defectList.get(row).getPriority()+"</td>");
					out.print("<td>"+defectList.get(row).getAssignee()+"</td>");
					out.print("<td>"+defectList.get(row).getType()+"</td>");
					out.print("<td>"+defectList.get(row).getStatus()+"</td>");
					out.print("<td>"+defectList.get(row).getSummary()+"</td>");
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
			
			out.print("<div class=\"addLinks\">");
			out.print("<a href=\"\">Add projects</a>");
			out.print("<a href=\"\">Add resolution</a>");
			out.print("<a href=\"\">Add priority</a>");
			out.print("<a href=\"\">Add type</a>");
			out.print("</div>");
			out.print("<div class=\"userAction\">");
			out.print("<a href=\"\">Add user</a>");
			out.print("<a href=\"\">Find user</a>");
			out.print("</div>");
			out.print("</body></html>");

			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error(IO_ERR + e.getMessage());
		}
	}

}
