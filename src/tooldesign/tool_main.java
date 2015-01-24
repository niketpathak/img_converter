package tooldesign;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class tool_main {
	private static String filepath = "/Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/ID_IMAGES/";
	
	public static void main(String args[]) throws NumberFormatException, SQLException{
		db_functions db_obj =  new db_functions();
		List<Student> student_details;
		try {
			student_details = db_obj.display_students();
			for(Student a: student_details){
//	             a.getStudent_num();
//				a.getFirst_name();
//				a.getStudent_ID();  
	            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File dir = new File(filepath);
		  File[] directoryListing = dir.listFiles(new ImageFileFilter());	//filter to get only Images
		  if (directoryListing != null) {
			  String totalment="";
		    for (File child : directoryListing) {
		    	String filename = child.getName();
		    	System.out.println(filename);
		    	String[] obt = filename.split("");					//split it into characters
			      StringBuffer result_buffer = new StringBuffer();
			      for (int i = 3; i < 7; i++) {						
			        	result_buffer.append(obt[i]);					//Get last 4-digits of ID!
			        }
			    String id_last_four = result_buffer.toString();
			    int student_id = Integer.parseInt(id_last_four);
			    Boolean isFileDone = db_obj.check_existing_record(student_id);
		    	if(!isFileDone){
				    recognize_text x = new recognize_text(filepath, filename,child.length(),student_id);
			    	String[] arr_c = x.recognize_textcontent();
//			    	String[] arr_c ={"LName","Fname","ID","true","1111"};
			    	if(arr_c[0].equals("undefined")){
			    		System.out.println("Unable to Identify Text");
			    	}
			    	else if(arr_c[0].equals("filetype_error")){
			    		System.out.println("Not an Image File  or File doesn't exist");	// error in specified file_path
			    	}
			    	else
			    	{
			    		totalment= totalment + arr_c[0]+arr_c[1]+arr_c[2] + arr_c[3]+ Integer.parseInt(arr_c[4])+" ";	//lname,fname,id,boolean for IDFRA
			    		db_obj.add_student(Integer.parseInt(arr_c[4]), arr_c[0], arr_c[1], arr_c[3], arr_c[2]);	//insert new records here!
			    	}
		    	}
		    }
//		    System.out.println(totalment);
		  } else {
		    // Handle the case where dir is not really a directory. or if dir is empty
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
			  System.out.println("No Images Present in "+filepath);
		  }
	}
	
}
