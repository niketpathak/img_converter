package tooldesign;

public class Student {
	private int student_num;
	private String student_ID;
	private String first_name;
	private String last_name;
	private String doc_type;
	
	public Student(int student_no, String fname, String lname, String doc_type2, String stud_idno) {
		this.student_num=student_no;
		this.first_name=fname;
		this.last_name=lname;
		this.doc_type=doc_type2;
		this.student_ID=stud_idno;
	}
	
	public int getStudent_num() {
		return student_num;
	}

	public void setStudent_num(int student_num) {
		this.student_num = student_num;
	}

	public String getStudent_ID() {
		return student_ID;
	}

	public void setStudent_ID(String student_ID) {
		this.student_ID = student_ID;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	


	
	
	
}
