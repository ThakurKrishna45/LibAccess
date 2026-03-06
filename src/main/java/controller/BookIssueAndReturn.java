package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import dao.BookIssueDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/issueBook", "/admin/returnBook"})
public class BookIssueAndReturn extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String action= req.getParameter("action");
		BookIssueDao issueDao = new BookIssueDao();
		
		if("issue".equals(action)) {
		    try {
		        String isbn = req.getParameter("isbn");
		        int userId = Integer.parseInt(req.getParameter("studentId"));

		        issueDao.issue(userId, isbn); 
		        res.sendRedirect("issueBook?msg=issued"); // Hits doGet -> forwards to JSP
		    } catch (Exception e) {
		   
		        try {
					res.sendRedirect("issueBook?error=" + java.net.URLEncoder.encode(e.getMessage(), "UTF-8"));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		}
			else if ("return".equals(action)) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            String isbn=req.getParameter("isbn");

            
            boolean isReturned=issueDao.returnBook(userId,isbn);
            try {
				if(isReturned) {
					res.sendRedirect("returnBook?msg=returned");
				} else {
					res.sendRedirect("returnBook?error=Book already returned or record not found");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
        }
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		    String path = req.getServletPath(); 
		    BookIssueDao issueDao = new BookIssueDao();
		    System.out.println("---------------"+path);
		    if (path.contains("returnBook")) {
		        req.setAttribute("issuedBooksList", issueDao.getAllBookIssues());
		        req.getRequestDispatcher("returnBook.jsp").forward(req, res);
		    } else {
		    	req.getRequestDispatcher("issueBook.jsp").forward(req, res);
		}
	}
	}
	
