//package filter;
//
//import java.io.IOException;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
////@WebFilter(urlPatterns = {"/admin/*"})
//public class RoleFilter implements Filter {
//
//    @Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//
//        HttpSession session = request.getSession(false);
//        String role = (String) session.getAttribute("role");
//
//        if (!"ADMIN".equals(role)) {
//            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
//            return;
//        }
//
//        chain.doFilter(req, res);
//    }
//}
