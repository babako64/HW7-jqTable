package ir.maktab.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ir.maktab.model.Student;


public class StudentDAOImp implements StudentDAO {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/mydb";

	private static final String USER = "root";
	private static final String PASS = "";

	/**
	 * Add new teacher to Database
	 * 
	 * @param {object} student
	 */
	@Override
	public void add(Student st) {

		Connection conn = null;
		Statement stmt = null;
		String sql=null;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String selectSQL = "SELECT id,name FROM student WHERE name='" + st.getName() + "' or id= '" + st.getId() + "'";
			ResultSet rs = stmt.executeQuery(selectSQL);
			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				if (st.getName().equals(name) || st.getId()==id) {
					System.out.println("Exist this Record");
					JOptionPane.showMessageDialog(null,"Exist this Student \n" + "Student ID : " + id,"Warning",JOptionPane.WARNING_MESSAGE);    
					System.out.println("ID: " + st.getId());
					return;
				}
			}
			
			
			if(st.getId()==0) {
			 sql = "INSERT INTO student (name, department,profesor_id) " + "VALUES('" + st.getName() + "','"
					+ st.getDept() + "','" + st.getSuperVisorId() + "')";
			}else {
				
				sql = "INSERT INTO student (id,name, department,profesor_id) " + "VALUES('" + st.getId() + "','" + st.getName() + "','"
						+ st.getDept() + "','" + st.getSuperVisorId() + "')";
			}
			stmt.executeUpdate(sql);

		} catch (Exception e) {

		} finally {

			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}
	
	
	/**
	 * Get student information from Database
	 * 
	 * @param {int} id
	 * 
	 * @return {list}
	 */
	@Override
	public ArrayList<Student> getStudent(int fId) {
		Connection conn = null;
		Statement stmt = null;
		ArrayList<Student> stList = new ArrayList<>();
		Student st = null;
		String name = null;
		int id;
		String dept = null;
		int prof_id;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT * FROM student WHERE student.id='" + fId + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("name");
				dept = rs.getString("department");
				prof_id = rs.getInt("profesor_id");
				st = new Student(name, id, dept, prof_id);
				stList.add(st);
			}

		} catch (Exception e) {

		} finally {

			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return stList;
	}

	/**
	 * 
	 * Remove student by id from database
	 * 
	 * @param {int} id
	 */
	@Override
	public void remove(int id) {

		Connection conn = null;
		Statement stmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "DELETE FROM student " + "WHERE id = '" + id + "'";
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {

		}

	}

	/**
	 * Get All student information from database
	 * 
	 * @return {list} teacher
	 */
	@Override
	public ArrayList<Student> getAll() {

		Connection conn = null;
		Statement stmt = null;
		ArrayList<Student> stList = new ArrayList<>();
		String name = null;
		int id;
		String dept = null;
		int prof_id;

		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT * FROM student ";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("name");
				dept = rs.getString("department");
				prof_id = rs.getInt("profesor_id");
				Student st = new Student(name, id, dept, prof_id);
				stList.add(st);
			}

		} catch (Exception e) {

		} finally {

			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return stList;
	}

	/**
	 * 
	 * Update student information
	 * 
	 * @param {int,string,string}
	 */
	@Override
	public void Update(int id, String stName, String dept, int profID) {

		Connection conn = null;
		Statement stmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "UPDATE student " + "SET name = '" + stName + "',department = '" + dept + "',profesor_id = '"
					+ profID + "' WHERE id = '" + id + "'";
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {

		}
	}
	
public void removeAll() {
		
		Connection conn = null;
		Statement stmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "DELETE FROM student";
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {

		}
	}

}
