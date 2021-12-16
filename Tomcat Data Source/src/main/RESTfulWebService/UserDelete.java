package main.RESTfulWebService;


import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import main.entities.Product;
import main.entities.Transaction;
import main.entities.User;
import main.service.Service;
import main.service.implementation.TransactionServiceImpl;
import main.service.implementation.UserServiceImpl;

@WebServlet("/UserDelete")
public class UserDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String reinput_password = request.getParameter("reinput_password");
		
		UserServiceImpl us = new UserServiceImpl();
		TransactionServiceImpl ts = new TransactionServiceImpl();
		
		JSONObject finalObj=new JSONObject(); 
		try {
			User userData = us.findUserByEmail(email);
			if(password.equals(reinput_password)) {
				if(givenPassword_whenHashing_thenVerifying(userData.getPassword(), password) ) {
					//check if user still have transaction with status not finished
					List<Transaction> listActiveTransaction = ts.findRunningTransactionByUserId(userData.getId());
					if(listActiveTransaction == null || listActiveTransaction.size() <= 0 ) {
						//Delete Account
						if(us.delete(email) == 1) {
							finalObj.put("status", "success"); 
						}else {
							finalObj.put("status", "failed");  
						}
					}else{
						finalObj.put("status", "still_have_active_transaction");  
					}
				}else {
					finalObj.put("status", "password_wrong");  
				}
			}else {
				finalObj.put("status", "password_not_same"); 
			}
			
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(finalObj);
	        out.flush();   
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
