package tooldesign;

import java.io.File;

public class tool_main {
	private static String filepath = "/Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/ID_IMAGES/";
	
	public static void main(String args[]){
		File dir = new File(filepath);
		  File[] directoryListing = dir.listFiles(new ImageFileFilter());	//filter to get only Images
		  if (directoryListing != null) {
			  String totalment="";
		    for (File child : directoryListing) {
		    	recognize_text x = new recognize_text(filepath, child.getName(),child.length());
		    	String[] arr_c = x.recognize_textcontent();
		    	if(arr_c[0].equals("undefined")){
		    		System.out.println("Unable to Identify Text");
		    	}
		    	else if(arr_c[0].equals("filetype_error")){
		    		System.out.println("Not an Image File  or File doesn't exist");	// error in specified file_path
		    	}
		    	else
		    	{
		    		totalment= totalment + arr_c[0]+arr_c[1]+arr_c[2] + arr_c[3]+" ";	//lname,fname,id,boolean for IDFRA
		    	}
		    }
		    System.out.println(totalment);
		  } else {
		    // Handle the case where dir is not really a directory. or if dir is empty
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
			  System.out.println("No Images Present in "+filepath);
		  }
	}
	
}
