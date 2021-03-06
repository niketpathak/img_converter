package test;
import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class launch_process {

	public static void main(String args[]) {
		 Boolean filetype_accepted=false;
        try {
        	
        	Runtime runtime = Runtime.getRuntime();
        	Process pr_i = runtime.exec("identify /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/PI5555.jpg");
//        	Process pr_i = runtime.exec("identify /Users/niketpathak/Documents/logo-gkm.png");

            BufferedReader input = new BufferedReader(new InputStreamReader(pr_i.getInputStream()));
            BufferedReader error_if_any = new BufferedReader(new InputStreamReader(pr_i.getErrorStream()));
            String line=null;
            String err =null;
            while((line=input.readLine()) != null) {
                System.out.println("Identify output->"+line);
//                String[] expected_ext = {"JPEG","PNG","TIFF"};
                Pattern p = Pattern.compile("(JPEG|TIFF|PNG)");		//using regex here -> http://www.tutorialspoint.com/java/java_regular_expressions.htm
                Matcher m = p.matcher(line);
                filetype_accepted = m.find();
            }
            while ((err = error_if_any.readLine()) != null) {
                System.out.println("This is Err_i Code->"+err);
            }
            int exitVal_i = pr_i.waitFor();
            System.out.println("Identify Exit error code "+exitVal_i);
            if(filetype_accepted && exitVal_i==0){
            	Process pr_grayscale = runtime.exec("convert /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/dpi.jpg -set colorspace Gray -separate -average /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/PI5555_grscl.jpg");
            	int exitVal_gr = pr_grayscale.waitFor();
            	 BufferedReader input_gr = new BufferedReader(new InputStreamReader(pr_grayscale.getInputStream()));
                 BufferedReader error_if_any_gr = new BufferedReader(new InputStreamReader(pr_grayscale.getErrorStream()));
                 line=null;err =null;
                 while((line=input_gr.readLine()) != null) {
                     System.out.println("output_grayscale->"+line);
                 	}
                 while ((err = error_if_any_gr.readLine()) != null) {
                     System.out.println("This is Err_gr Code->"+err);
                 }
            	System.out.println("Grayscale Exit error code "+exitVal_gr);
                Process pr_t = runtime.exec("tesseract /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/PI5555_grscl.jpg /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/niket1 -l eng+fra");
                BufferedReader input_t = new BufferedReader(new InputStreamReader(pr_t.getInputStream()));
                BufferedReader error_if_any_t = new BufferedReader(new InputStreamReader(pr_t.getErrorStream()));
                line=null;err =null;
                while((line=input_t.readLine()) != null) {
                    System.out.println("output_tesseract->"+line);
                	}
                while ((err = error_if_any_t.readLine()) != null) {
                    System.out.println("This is Err_t Code->"+err);
                }
                int exitVal_t = pr_t.waitFor();
                System.out.println("Tesseract Exit error code "+exitVal_t);
            
            } else {
            	System.out.println("Unsupported File Type");
            }
            String file_path_ts = "/Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/niket1.txt";
            BufferedReader file_readd = new BufferedReader(new InputStreamReader(new FileInputStream(file_path_ts)));
            int linecount=0;
//            String[] file_cnt_arry = new String[30];
            String text_cnt = "";
            while((line=file_readd.readLine()) != null) {
             	 line=line.replaceAll("\\s+","");		//eliminate white-spaces
             	text_cnt = text_cnt +" "+ line;
//            	     file_cnt_arry[linecount]=line;
            	     linecount++;
            	}
            System.out.println("FILE_CONTENT->"+text_cnt);
            String[] split_con= text_cnt.split(" ");
            String process_fn =  split_con[split_con.length-2];	
            String process_ln =  split_con[split_con.length-1];
//            System.out.println(Arrays.toString(split_con));
            System.out.println("FName_p->"+process_fn+" LName_p->"+process_ln);
            int br_pt=process_fn.indexOf("<")+1;
            System.out.println(br_pt);
            String[] obt = process_fn.split("");					//split it into characters
          StringBuffer result_buffer = new StringBuffer();
          for (int i = 0; i < 6; i++) {						
            	result_buffer.append(obt[i]);					//Get IDFRA!
            }
          String chk_strr = result_buffer.toString();
          System.out.println("CheckString->"+chk_strr);
          Boolean isID=false;
          if(chk_strr.trim().equalsIgnoreCase("IDFRA")){
        	  isID=true;
          }
          System.out.println("chk str Bool->"+isID);
          result_buffer=new StringBuffer();
          for (int i = 6; i < br_pt; i++) {						
          	result_buffer.append(obt[i]);					//append the char viz fname!
          }
          String fname = result_buffer.toString().trim();
         System.out.println("FirstName->"+fname);
         obt=process_ln.split("");
         result_buffer=new StringBuffer();
         for (int i = 0; i < 13; i++) {						
           	result_buffer.append(obt[i]);					//append the char viz ID!
           }
         String id_f = result_buffer.toString().trim();
         System.out.println("ID->"+id_f);
         result_buffer=new StringBuffer();
         for (int i = 14; i < process_ln.length()-8; i++) {						
            	result_buffer.append(obt[i]);					//append the char viz lname with <<!
            }
         String lname = result_buffer.toString();
         lname = lname.replaceAll("<<|<", " ").trim();					//remove </<<+
         System.out.println("f_name->"+lname);
 
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        
    }
	
}
