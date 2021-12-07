package main.RESTfulWebService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.entities.Product;
import main.service.implementation.ProductsServiceImpl;

public class FindProductByDetailID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FindProductByDetailID() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductsServiceImpl ps = new ProductsServiceImpl();
		
		try {
			String URI = request.getRequestURI();
			int id = Integer.valueOf(URI.substring(URI.lastIndexOf('/') + 1) );
			Product product = ps.findByDetailID(id);
			request.setAttribute("product", product);
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			String ok = objectMapper.writeValueAsString(product);
			
			ObjectMapper mapper = new ObjectMapper();
		    JsonNode actualObj = mapper.readTree(ok);
			
		    PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(actualObj);
	        out.flush();   
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
