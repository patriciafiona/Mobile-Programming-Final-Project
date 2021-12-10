package main.RESTfulWebService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.entities.Product;
import main.service.implementation.ProductsServiceImpl;

public class FindProductByName extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindProductByName() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductsServiceImpl ps = new ProductsServiceImpl();
		
		try {
			String URI = request.getRequestURI();
			String name = URI.substring(URI.lastIndexOf('/') + 1).toString();
			List<Product> listProduct = ps.findByName(name);
			request.setAttribute("listProduct", listProduct);
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			String ok = objectMapper.writeValueAsString(listProduct);
			
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
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
