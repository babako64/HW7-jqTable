package ir.maktab.model;

public class Student {

	private String name;
	private int id;
	private String dept;
	private int superVisorId;
	
	public Student(String name, int id, String dept, int superVisorId) {
		super();
		this.name = name;
		this.id = id;
		this.dept = dept;
		this.superVisorId = superVisorId;
	}
	
	public Student() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getSuperVisorId() {
		return superVisorId;
	}

	public void setSuperVisorId(int superVisorId) {
		this.superVisorId = superVisorId;
	}
	
	
}
