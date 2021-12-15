package main.RESTfulWebService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import main.entities.User;
import main.service.implementation.UserServiceImpl;

@WebServlet("/UserLogout")
public class UserLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserLogout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession(false);
		
		//update login Status to 0
		String email = request.getParameter("email");
		UserServiceImpl us = new UserServiceImpl();
		JSONObject finalObj=new JSONObject(); 
		try {
			User userData = us.findUserByEmail(email);
			if(us.updateLoginStatus(userData.getId(), 0) == 1) {
				finalObj.put("status", "success"); 
			}else {
				finalObj.put("status", "failed"); 
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(finalObj);
        out.flush();   
	}

}
