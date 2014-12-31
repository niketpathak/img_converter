package test;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class launch_process {

	public static void main(String args[]) {
		 Boolean filetype_accepted=false;
        try {

        	Runtime runtime_i = Runtime.getRuntime();
        	Process pr_i = runtime_i.exec("identify /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/recomm1.jpg");
//        	Process pr_i = runtime_i.exec("identify /Users/niketpathak/Documents/logo-gkm.png");

            BufferedReader input = new BufferedReader(new InputStreamReader(pr_i.getInputStream()));
            BufferedReader error_if_any = new BufferedReader(new InputStreamReader(pr_i.getErrorStream()));
            String line=null;
            String err =null;
            while((line=input.readLine()) != null) {
                System.out.println("This is the output->"+line);
//                String[] expected_ext = {"JPEG","PNG","TIFF"};
                Pattern p = Pattern.compile("(JPEG|TIFF|PNG)");		//using regex here -> http://www.tutorialspoint.com/java/java_regular_expressions.htm
                Matcher m = p.matcher(line);
                filetype_accepted = m.find();
            }
            
            while ((err = error_if_any.readLine()) != null) {
                System.out.println("This is Err_i Code->"+err);
            }
            int exitVal_i = pr_i.waitFor();
            System.out.println("Exited identify with error code "+exitVal_i);
            if(filetype_accepted && exitVal_i==0){
            	Runtime runtime_t = Runtime.getRuntime();
                Process pr_t = runtime_t.exec("tesseract /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/recomm1.jpg /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/niket1 -l eng");
                BufferedReader input_t = new BufferedReader(new InputStreamReader(pr_t.getInputStream()));
                BufferedReader error_if_any_t = new BufferedReader(new InputStreamReader(pr_t.getErrorStream()));
                line=null;err =null;
                while((line=input_t.readLine()) != null) {
                    System.out.println("This is the output->"+line);
                	}
                while ((err = error_if_any_t.readLine()) != null) {
                    System.out.println("This is Err_t Code->"+err);
                }
                int exitVal_t = pr_i.waitFor();
                System.out.println("Exited tesseract with error code "+exitVal_t);
            
            } else {
            	System.out.println("Unsupported File Type");
            }
//            System.out.println("Boolen value is->"+filetype_accepted);	//this variable is accessible globally

        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        
    }
	
}
