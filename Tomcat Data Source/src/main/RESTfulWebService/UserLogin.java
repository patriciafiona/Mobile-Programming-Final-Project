package main.RESTfulWebService;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import main.entities.User;
import main.service.implementation.UserServiceImpl;

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		JSONObject finalObj=new JSONObject(); 
		if(!email.isEmpty() && !password.isEmpty() ) {
			UserServiceImpl us = new UserServiceImpl();
			try {
				User userData = us.findUserByEmail(email);
				if(userData != null) {
					if(userData.getIsLogin() == 1) {
						finalObj.put("status", "failed_still_login"); 
						System.out.println("UPDATE STATUS: failed_still_login");
					}else {
						if(userData.getEmail().equals(email) && givenPassword_whenHashing_thenVerifying(userData.getPassword(), password) ) {
							//Login Success go to home page
							if(us.updateLoginStatus(userData.getId(), 1) == 1) {
								finalObj.put("status", "success"); 
								System.out.println("UPDATE STATUS: success");
							}else {
								finalObj.put("status", "failed_update_data"); 
								System.out.println("UPDATE STATUS: failed_update_data");
							}
						}else {
							finalObj.put("status", "email_or_password_wrong"); 
							System.out.println("UPDATE STATUS: email_or_password_wrong");
						}
					}
				}else {
					finalObj.put("status", "user_not_register"); 
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			finalObj.put("status", "email_or_password_empty"); 
			System.out.println("UPDATE STATUS: email_or_password_empty");
		}
		
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(finalObj);
        out.flush();  
	}
	
	private boolean givenPassword_whenHashing_thenVerifying(String hashPwd, String inputPwd){
	    // Create MessageDigest instance for MD5
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			//Add password bytes to digest
	        md.update(inputPwd.getBytes());
	        //Get the hash's bytes 
	        byte[] bytes = md.digest();
	        //This bytes[] has bytes in decimal format;
	        //Convert it to hexadecimal format
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++)
	        {
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
		        
	        return (sb.toString().equals(hashPwd) ) ;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return false;
	}

}
