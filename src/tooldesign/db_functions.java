package tooldesign;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
;

/**
 *
 * @author Niket Pathak
 */
public class db_functions {
        
    private Connection cn=null;
    public Boolean connection_status=false;
    private Statement stmt=null;
    
    public db_functions(){
         try {
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ocr_db","root","");
            connection_status=true;
        } catch (Exception e) {
             e.printStackTrace();
             System.out.println("ERROR: Unable to Connect to Database.");
        }
    }
    
    public List<Student> display_students() throws SQLException{

            List<Student> students = new ArrayList<Student>();
            String sql="select student_number,first_name,last_name,doc_type,id_num from student_details";
            Statement stmt = cn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                    int student_no = res.getInt("student_number");
                    String fname = res.getString("first_name");
                    String lname = res.getString("last_name");
                    String doc_type = res.getString("doc_type");
                    String stud_idno = res.getString("id_num");
                    Student a = new Student(student_no,fname,lname,doc_type,stud_idno);
                    students.add(a);
            }
            if(cn!=null){
            	cn.close();
            }
            return students;
    }
    
    public Boolean check_existing_record(int stud_id) throws SQLException{
    	Boolean check =false;
    	PreparedStatement stmt;
    	String sql="select id from ocr_db.student_details where student_number=?";
        stmt = cn.prepareStatement(sql);
        stmt.setInt(1, stud_id);
        ResultSet res = stmt.executeQuery();
        while(res.next()){
                check=true;
        }
    	return check;
    }
    
    public void add_student(int std_no, String lname, String fname, String dtype, String identity_no) throws SQLException{
    	if(dtype=="true"){
    		dtype="Identity Card";
    	}else dtype="Unknown";
     String sql="insert into student_details (student_number, last_name, first_name, doc_type, id_num) values (?,?,?,?,?)";
            PreparedStatement stmt;
        try {
            stmt = cn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, std_no);
            stmt.setString(2, lname);
            stmt.setString(3, fname);
            stmt.setString(4, dtype);
            stmt.setString(5, identity_no);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int numero=0;
            if (rs.next()){
                numero=rs.getInt(1);
            }
//            return numero;
        } catch (SQLException ex) {
            Logger.getLogger(db_functions.class.getName()).log(Level.SEVERE, null, ex);
//            return 0;
        }
        finally{
        	if(cn!=null){
        		cn.close();
        	}
        }
           
    }
    

}
