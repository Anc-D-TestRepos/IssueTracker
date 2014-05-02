package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class loginFilter
 */
@WebFilter(urlPatterns = { "/Authentification" },servletNames = { "Authentification" })
public class loginFilter implements Filter {
	private final String EMAIL = "emailAdress";
	private final String PASS = "pass"; 
	private final String ERR_PAGE = "/ErrorPage";
    /**
     * Default constructor. 
     */
    public loginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String email;
		String password;
		
		email = request.getParameter(EMAIL);
		password = request.getParameter(PASS);
		
			
		if(((email==null)|(password==null)|((email.isEmpty()|(password.isEmpty()))))){
			

			RequestDispatcher rd = request.getRequestDispatcher(ERR_PAGE);
			rd.forward(request, response);
			
		}else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
