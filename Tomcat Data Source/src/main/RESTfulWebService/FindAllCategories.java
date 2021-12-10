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

import main.entities.Category;
import main.service.implementation.CategoryServiceImpl;

@WebServlet("/FindAllCategories")
public class FindAllCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public FindAllCategories() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setHeader("Access-Control-Allow-Origin", "*");
		
		CategoryServiceImpl cs = new CategoryServiceImpl();
		try {
			List<Category> listCategories = cs.findAll();
			request.setAttribute("listCategories", listCategories);
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			String ok = objectMapper.writeValueAsString(listCategories);
			
			ObjectMapper mapper = new ObjectMapper();
		    JsonNode actualObj = mapper.readTree(ok);
		    
		    //adding status & convert to JSON object
		    JSONObject finalObj=new JSONObject(); 
		    finalObj.put("status", 200); 
		    finalObj.put("results", actualObj);
			
		    PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(finalObj);
	        out.flush();   
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
