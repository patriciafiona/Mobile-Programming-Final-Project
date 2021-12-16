package main.RESTfulWebService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import main.entities.Transaction;
import main.entities.User;
import main.service.implementation.TransactionServiceImpl;
import main.service.implementation.UserServiceImpl;

@WebServlet("/TransactionInsert")
public class TransactionInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TransactionInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String transaction_id = request.getParameter("transaction_id");
		String email = request.getParameter("email");
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int color_id = Integer.parseInt(request.getParameter("color_id"));
		int discount = Integer.parseInt(request.getParameter("discount"));
		double price = Double.parseDouble(request.getParameter("price"));
		double total_all_price = Double.parseDouble(request.getParameter("total_all_price"));
		int total_all_product = Integer.parseInt(request.getParameter("total_all_product"));
		int size = Integer.parseInt(request.getParameter("size"));
		int status_id = Integer.parseInt(request.getParameter("status_id"));
		
		JSONObject finalObj=new JSONObject(); 
		
		if(transaction_id != null && !email.isEmpty()) {
			UserServiceImpl us = new UserServiceImpl();
			TransactionServiceImpl ts = new TransactionServiceImpl();
			
			try {
				User userData = us.findUserByEmail(email);
				
				//try to check to database email already registered or not
				if(userData != null) {
					Transaction data = new Transaction(
							transaction_id,
							userData.getId(),
							product_id,
							quantity,
							color_id,
							discount,
							price,
							total_all_price,
							total_all_product,
							size,
							status_id
							);
					
					//insert data transaction
					if(ts.insert(data) == 1) {
						finalObj.put("status", "success"); 
						System.out.println("INSERT STATUS: success");
					}else {
						finalObj.put("status", "failed"); 
						System.out.println("INSERT STATUS: failed");
					}
				}else {
					finalObj.put("status", "user_not_found"); 
					System.out.println("INSERT STATUS: user_not_found");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("INSERT STATUS: data is empty");
		}
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(finalObj);
        out.flush();   
	}

}
