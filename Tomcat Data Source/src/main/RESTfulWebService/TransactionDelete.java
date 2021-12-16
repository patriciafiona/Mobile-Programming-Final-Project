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

import main.service.implementation.TransactionServiceImpl;
import main.service.implementation.UserServiceImpl;

@WebServlet("/TransactionDelete")
public class TransactionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TransactionDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String transaction_id = request.getParameter("transaction_id");
		
		JSONObject finalObj=new JSONObject(); 
		
		if(transaction_id != null) {
			TransactionServiceImpl ts = new TransactionServiceImpl();
			try {
				if(ts.delete(transaction_id) == 1) {
					finalObj.put("status", "success"); 
				}else {
					finalObj.put("status", "failed"); 
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(finalObj);
        out.flush();   
	}

}
