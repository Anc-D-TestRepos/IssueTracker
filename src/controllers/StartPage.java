package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import beans.Defect;
import service.DefectSearcher;

/**
 * Servlet implementation class StartPage
 */
@WebServlet("/StartPage")
public class StartPage extends HttpServlet {
	private final String  HAS_DEFECT = "hasDefect";
	private final String  IO_ERR = "I\\O error in StartPage - ";
	private final String PATH = "WEB-INF/resource/defectData.xml";
	private final String LOG_ATRR = "log4";
	Logger logger ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartPage() {
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
		boolean hasDefect = false;
		logger = (Logger)getServletContext().getAttribute(LOG_ATRR);
		HttpSession session = req.getSession();
		session.setAttribute(HAS_DEFECT, hasDefect);////
		req.setAttribute(HAS_DEFECT, hasDefect);
		InputStream stream = getServletContext().getResourceAsStream(PATH);
		DefectSearcher defectSearch = new DefectSearcher(stream);
		List<Defect> defectList = defectSearch.findDefects(capacity);
				
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		
		try {
			out = res.getWriter();
			out.print("<!DOCTYPE html>");
			out.print("<html lang=\"en\"><head>");
			out.print(" <meta charset=\"UTF-8\"> <title>Issue Tracker</title>");
			out.print("<style type=\"text/css\">");
			out.print("body {font-size: 20px;}"
						+".description {margin: 50px 0 0 10px;font-size: 17px;}"
						+".inputData {text-align: center;display: inline-block;}"
						+"span.email {padding: 10px;}"
						+"span.pass {padding: 11px;}"
						+"div.email {padding: 10px;display: inline-block;}"
						+"div.pass {padding: 10px;display: inline-block;}"
						+"div.button {display: inline-block;padding: 0;margin:  10px 0 0 0;}"
						+".defectList table {	border: solid 1px;border-spacing: 0px;width: 100%;margin: 40px 0 0 0 ;}"
						+".defectList table td,th {border-collapse: collapse;height: 25px;border: solid 1px;}");
			out.print("</style></head>");
			out.print("<body>");
			out.print("<div class=\"description\">Please enter yuor email adress and password</div>");
			out.print("<div class=\"inputData\">");
			out.print("<form method=\"POST\" action=\"Authentification\">");
			out.print("<div class=\"email\"><input type=\"text\" name=\"emailAdress\"><span class=\"email\"> email</span></div>");
			out.print("<div class=\"pass\"><input type=\"password\" name=\"pass\"><span class=\"pass\"> pass</span></div>");
			out.print("<div class=\"button\"><input type=\"submit\" value=\"   enter   \"></div>");
			out.print("</form></div>");
			out.print("<hr color=\"red\">");
			
			if ((defectList!=null) & (!defectList.isEmpty())){
				hasDefect=true;
				session.setAttribute("hasDefect", hasDefect);
				out.print("<div class=\"defectList\">");
				out.print("<table><tr>");
				out.print(	"<th><a href=\"\">Id</a></th>"
							+"<th><a href=\"\">Priority</a></th>"
							+"<th><a href=\"\">Assignee</a></th>"
							+"<th><a href=\"\">Type</a></th>"
							+"<th><a href=\"\">Status</a></th>"
							+"<th><a href=\"\">Summary</a></th>");
				out.print("</tr>");
				
				for( int row=0; row <= defectList.size()-1; row++){
					out.print("<tr>");
					out.print("<td>" + defectList.get(row).getId() + "</td>");
					out.print("<td>" + defectList.get(row).getPriority() + "</td>");
					out.print("<td>" + defectList.get(row).getAssignee() + "</td>");
					out.print("<td>" + defectList.get(row).getType() + "</td>");
					out.print("<td>" + defectList.get(row).getStatus() + "</td>");
					out.print("<td>" + defectList.get(row).getSummary() + "</td>");
					out.print("</tr>");
				}
				
				out.print("</table></div>");
			}else{
				out.print("<p align=\"center\">Application doesnt have any defects </p>");
			}
			out.print( "</body></html>");
			
			out.flush();
			out.close();
			
		} catch (IOException e) {
			
			logger.error(IO_ERR + e.getMessage());
		}
	}
}


