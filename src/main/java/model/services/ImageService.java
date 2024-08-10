package model.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageService {

	private final String uploadPath = "/Users/caiolopes/images/";

	public void retrieve(HttpServletRequest request, HttpServletResponse response) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream output = null;

		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

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
	    		  if(fis != null) {
					fis.close();
				}
		    	  if(bis != null) {
					bis.close();
				}
		    	  if(output != null) {
					output.close();
				}
	    	  }catch(IOException e) {
	    		  e.printStackTrace();
	    	  }
	      }
	}
}
