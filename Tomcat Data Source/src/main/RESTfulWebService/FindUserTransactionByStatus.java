package main.RESTfulWebService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.entities.Transaction;
import main.service.implementation.TransactionServiceImpl;

@WebServlet("/FindUserTransactionByStatus")
public class FindUserTransactionByStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindUserTransactionByStatus() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransactionServiceImpl ts = new TransactionServiceImpl();
		int user_id = Integer.valueOf(request.getParameter("user_id")); 
		int status_transaction = Integer.valueOf(request.getParameter("status")); 
		
		JSONObject finalObj=new JSONObject(); 
		try {
			List<Transaction> listTransaction = ts.findUserTransactionByUserIdAndStatus(user_id, status_transaction);
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			String ok = objectMapper.writeValueAsString(listTransaction);
			
			ObjectMapper mapper = new ObjectMapper();
		    JsonNode actualObj = mapper.readTree(ok);
		    
		    //adding status & convert to JSON object
		    if(listTransaction!=null) {
		    	finalObj.put("status", 200); 
		    }else {
		    	finalObj.put("status", 400); 
		    }
		    finalObj.put("results", actualObj);
			
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(finalObj);
	        out.flush(); 
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
