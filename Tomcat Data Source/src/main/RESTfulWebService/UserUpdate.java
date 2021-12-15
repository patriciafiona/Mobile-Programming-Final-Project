package main.RESTfulWebService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import main.entities.User;
import main.service.implementation.UserServiceImpl;

@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = ConvertResult(request.getParameter("name") );
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String bod = request.getParameter("birthday");
		
		JSONObject finalObj=new JSONObject(); 
		
		if(bod != null && !name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
			UserServiceImpl us = new UserServiceImpl();
			
			User user = new User(name, email, encryptPassword(password), phoneNumber, address, bod, 0);
			
			try {
				//try to check to database email already registered or not
				if(us.update(user) == 1) {
					finalObj.put("status", "success");  
				}else {
					finalObj.put("status", "failed");  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String ConvertResult(String val) {
		String temp = "";
		try {
			temp = new String(val.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	
	private String encryptPassword(String passwordToHash) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        
        return "";
	}

}
