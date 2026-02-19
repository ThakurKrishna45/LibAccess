package controller;

import java.io.IOException;
import java.util.List;

import dao.AdminDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/approveAdmin")
public class ApproveAdmin extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		AdminDao adao= new AdminDao();
		List<User> admins=adao.adminList();
		req.setAttribute("admins", admins);
		req.getRequestDispatcher("superAdmin/approveAdmin.jsp").forward(req,res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		int id=Integer.parseInt(req.getParameter("user_id"));
		AdminDao adao= new AdminDao();
		boolean isApproved= adao.approveAdmin(id);
		
            try {
            	if (isApproved) {
				res.sendRedirect("approveAdmin?status=activated");
            	  } else {
                      res.sendRedirect("approveAdmin?status=error");
                  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
	}
}
