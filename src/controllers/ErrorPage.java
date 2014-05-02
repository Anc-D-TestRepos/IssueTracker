package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ErrorPage
 */
@WebServlet("/ErrorPage")
public class ErrorPage extends HttpServlet {
	private final String  IO_ERR = "I\\O error in ErrorPage - ";
	private final String LOG_ATRR = "log4";
	Logger logger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErrorPage() {
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
		logger = (Logger)getServletContext().getAttribute(LOG_ATRR);
		res.setContentType("text/html; charset=utf-8");
		
		PrintWriter out;
		try {
			out = res.getWriter();
			out.print("<!DOCTYPE html>");
			out.print("<html lang=\"en\"><head>");
			out.print(" <meta charset=\"UTF-8\"> <title>Issue Tracker</title>");
			out.print("<style type=\"text/css\">");
			out.print(".message {text-align: center;font-size: 20px;padding: 50px;}"
						+"div.link {text-align: center;	font-size: 20px;}"
						+"hr {margin: 50px 0 0 0;	width: 100%;}");
			out.print(" </style></head>");
			out.print("<body>");
			out.print("<hr color=\"red\">");
			out.print("<div class=\"message\">Entered email or password is wrong</div>");
			out.print("<div class=\"link\">");
			out.print("<a href=\"StartPage\">try again</a>");
			out.print( "</div></body></html>");
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error(IO_ERR + e.getMessage());
		}
	}
	
}
