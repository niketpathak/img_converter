package test;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
public class launch_new_process {

		//this is the alternate version of the file. But it's not working even after adding the /usr/bin file path

public static void main(String [] args) throws IOException {
        
//		String command = "tesseract /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/recomm1.jpg /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/niket1 -l eng";
        String[] command = {"tesseract /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/recomm1.jpg /Users/niketpathak/Documents/ISEP_sem2/IMG_processing/Research/img2text_test/niket1 -l eng"};
        ProcessBuilder probuilder = new ProcessBuilder( command );
        
//        final Map<String, String> environment = probuilder.environment();	//setting the path
//        final String path = environment.get("PATH");
//        environment.put("PATH", "/mypath" + File.pathSeparator + path);
        
        //You can set up your work directory
        probuilder.directory(new File("/usr/local/bin"));
        
        Process process = probuilder.start();
      
        //Read out dir output
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        String err =null;
        BufferedReader error_if_any = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        System.out.printf("Output of running %s is:\n",
                Arrays.toString(command));
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        while ((err = error_if_any.readLine()) != null) {
            System.out.println(err);
        }
        //Wait to get exit value
        try {
            int exitValue = process.waitFor();
            System.out.println("\n\nExit Value is " + exitValue);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
 
//       public static void main(String args[]) {
// 
//    	   File wd = new File("/bin");			//specify cwd ici! 
//    	   System.out.println(wd);	
//    	   Process proc = null;
//    	   try {
//    	      proc = Runtime.getRuntime().exec("/bin/bash", null, wd);	//running script
//    	   }
//    	   catch (IOException e) {
//    	      e.printStackTrace();
//    	   }
//    	   if (proc != null) {
//    	      BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//    	      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
//    	      out.println("cd");
//    	      out.println("pwd");
//    	      out.println("cd Documents/ISEP_sem2/IMG_processing/Research/img2text_test");
//    	      out.println("ls");
//    	      out.println("tesseract recomm1.jpg ton -l eng");		// -l specifies language //pas marche? pourquoi?     
////    	      out.println("exit");
//    	      try {
//    	         String line;
//    	         while ((line = in.readLine()) != null) {
//    	            System.out.println(line);
//    	         }
//    	         proc.waitFor();
//    	         in.close();
//    	         out.close();
//    	         proc.destroy();
//    	      }
//    	      catch (Exception e) {
//    	         e.printStackTrace();
//    	      }
//    	   }
//       }
       }