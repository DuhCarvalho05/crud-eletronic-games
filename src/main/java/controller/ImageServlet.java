package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream output = null;
		
		String uploadPath = "/Users/caiolopes/images/";
		
		File uploadDir = new File(uploadPath);
		
		if (!uploadDir.exists()) uploadDir.mkdir();
		
		try{
			 String fileName = request.getPathInfo().substring(1);          
	         fis = new FileInputStream(new File(uploadPath+fileName));
	         bis = new BufferedInputStream(fis);        
	         response.setContentType("image/jpeg");
	         output = new BufferedOutputStream(response.getOutputStream());
	         for (int data; (data = bis.read()) > -1;) {
	           output.write(data);
	         }           
	      }
	      catch(IOException e){
	    	  e.printStackTrace();
	      }finally{
	    	  try {
	    		  if(fis != null)
	    			  fis.close();
		    	  if(bis != null) 
		    		  bis.close();
		    	  if(output != null)
		    		  output.close();
	    	  }catch(IOException e) {
	    		  e.printStackTrace();
	    	  }
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
