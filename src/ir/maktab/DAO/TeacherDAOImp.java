package ir.maktab.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ir.maktab.model.Teacher;

public class TeacherDAOImp implements TeacherDAO {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/mydb";

	private static final String USER = "root";
	private static final String PASS = "";

	private final static String TEACHER_TABLE = "profesor";


	/**
	 * Add new teacher to Database
	 * 
	 * @param {object} teah
	 */
	@Override
	public void add(Teacher tech) {
		Connection conn = null;
		Statement stmt = null;
		String sql;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String selectSQL = "SELECT id,name FROM profesor WHERE name='" + tech.getName() + "' or id= '" + tech.getId() +"'";
			ResultSet rs = stmt.executeQuery(selectSQL);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				if (tech.getName().equals(name) || tech.getId()==id) {
					System.out.println("Exist this Record");
					JOptionPane.showMessageDialog(null,"Exist this Teacher \n" +"Teacher ID :" + id,"Warning",JOptionPane.WARNING_MESSAGE); 
					System.out.println("ID: " + tech.getId());
					return;

				}
			}
			
			if(tech.getId()==0) {
			 sql = "INSERT INTO profesor (name, address) " + "VALUES('" + tech.getName() + "','"
					+ tech.getAddress() + "')";
			}else {
				
				 sql = "INSERT INTO profesor (id,name, address) " + "VALUES('" + tech.getId() + "','" + tech.getName() + "','"
						+ tech.getAddress() + "')";
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
	 * Get All teacher information from database
	 * 
	 * @return {list} teacher
	 */
	@Override
	public ArrayList<Teacher> getAll() {
		Connection conn = null;
		Statement stmt = null;
		ArrayList<Teacher> techList = new ArrayList<>();
		String name = null;
		int id;
		String add = null;

		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT * FROM profesor ";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("name");
				add = rs.getString("address");

				Teacher tech = new Teacher(id, name, add);
				techList.add(tech);
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

		return techList;
	}

	/**
	 * Get teacher information from Database
	 * 
	 * @param {int} id
	 * 
	 * @return {list}
	 */
	@Override
	public ArrayList<Teacher> getTeacher(int fid) {

		Connection conn = null;
		Statement stmt = null;
		ArrayList<Teacher> teachList = new ArrayList<>();
		Teacher st = null;
		String name = null;
		int id;
		String address = null;
		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			String sql = "SELECT * FROM profesor WHERE profesor.id='" + fid + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				id = rs.getInt("id");
				name = rs.getString("name");
				address = rs.getString("address");
				st = new Teacher(id, name, address);
				teachList.add(st);
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

		return teachList;
	}

	/**
	 * 
	 * Remove teacher
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

			String sql = "DELETE FROM profesor " + "WHERE id = '" + id + "'";
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {

		}

	}

	/**
	 * 
	 * Update teacher information
	 * 
	 * @param {int,string,string}
	 */
	
	@Override
	public void Update(int id, String TechName, String add) {
		Connection conn = null;
		Statement stmt = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String sql = "UPDATE profesor " + "SET name = '" + TechName + "',address = '" + add + "' WHERE id = '" + id
					+ "'";
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

			String sql = "DELETE FROM profesor";
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();

		} catch (Exception e) {

		}

	}
}
