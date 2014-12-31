package test;
import java.io.*;

public class launch_process {

	public static void main(String args[]) {
		 
        try {
//            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
        	
//            File file = new File("/Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test");         
//        	File file = new File("/usr/local/bin");     // this is actually the location of Tesseract Prog    
//        	, null, file   
        	Runtime runtime = Runtime.getRuntime();
            Process pr = runtime.exec("tesseract /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/recomm1.jpg /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/niket1 -l eng");
//        	Process pr = runtime.exec("identify /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/recomm1.jpg");

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            BufferedReader error_if_any = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            String line=null;
            String err =null;
            while((line=input.readLine()) != null) {
                System.out.println(line);
            }
            while ((err = error_if_any.readLine()) != null) {
                System.out.println(err);
            }
            int exitVal = pr.waitFor();
            System.out.println("Exited with error code "+exitVal);

        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
	
}
