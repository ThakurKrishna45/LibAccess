package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/admin/*", "/user/*", "/superAdmin/*"})
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        
        String role = (session != null) ? (String) session.getAttribute("userRole") : null;
        String path = request.getServletPath();
        if ("super admin".equals(role)) {
            chain.doFilter(req, res);
            return;
        }

        if ("admin".equals(role) && (path.startsWith("/admin") || path.startsWith("/user"))) {
            chain.doFilter(req, res);
            return;
        }


        if ("student".equals(role) && path.startsWith("/user")) {
            chain.doFilter(req, res);
            return;
        }

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
    }
}