package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class SymbolFilter
 */
@WebFilter(urlPatterns = { "/Authentification" }, servletNames = { "Authentification" })
public class SymbolFilter implements Filter {
	private final String EMAIL = "emailAdress";
	private final String PASS = "pass"; 
    /**
     * Default constructor. 
     */
    public SymbolFilter() {
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
	
		email=encodeHtmlTag(email);
		password=encodeHtmlTag(password);
		
		request.setAttribute(EMAIL, email);
		request.setAttribute(PASS, password);
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	
	private  String encodeHtmlTag(String tag) {

        if (tag == null) {
            return null;
        }

        int length = tag.length();
        StringBuffer encodedTag = new StringBuffer(2 * length);

        for (int i = 0; i < length; i++) {
            char c = tag.charAt(i);
            if (c == '<') {
                encodedTag.append("&lt;");
            } else if (c == '>') {
                encodedTag.append("&gt;");
            } else if (c == '&') {
                encodedTag.append("&amp;");
            } else if (c == '"') {
                encodedTag.append("&quot;");
            } else if (c == ' ') {
                encodedTag.append("&nbsp;");
            } else {
                encodedTag.append(c);
            }
        }

        return encodedTag.toString();
    }	
	
}
