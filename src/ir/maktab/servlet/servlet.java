package ir.maktab.servlet;

import java.awt.print.Printable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ir.maktab.DAO.StudentDAOImp;
import ir.maktab.DAO.TeacherDAOImp;
import ir.maktab.model.Student;
import ir.maktab.model.Teacher;
/**
 * Servlet implementation class servlet
 */
@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw = response.getWriter();
		
		String button = request.getParameter("button");
		String tbutton = request.getParameter("tbutton");
		
		String name="",id="",dep="",supid="",tid="",tname="",address="";
		
		 name = request.getParameter("name");
		 id = request.getParameter("id");
		 dep = request.getParameter("department");
		 supid = request.getParameter("supid");
		
		 tname = request.getParameter("tname");
		 tid = request.getParameter("tid");
		 address = request.getParameter("address");
		 
		if(button!=null) {
		student(button,name,id,dep,supid,pw);
		
	}else{
		 teacher(tbutton,tid,tname,address,pw);
	}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void student(String button, String name, String id, String dep, String supid,PrintWriter pw) {
		
		StudentDAOImp stDAO = new StudentDAOImp();
		
		switch(button) {
		
		case "add":
			

			if(!name.equals("") && !id.equals("") && !dep.equals("") && !supid.equals("")) {
			Student st = new Student(name, Integer.parseInt(id), dep, Integer.parseInt(supid));
			stDAO.add(st);
			}
			break;
		case "remove":
			
			 if(!id.equals("") ) {
					stDAO.remove(Integer.parseInt(id));
					}
			break;
		case "update":

			 if(!name.equals("") && !id.equals("") && !dep.equals("") && !supid.equals("")) {
					
					stDAO.Update(Integer.parseInt(id), name, dep, Integer.parseInt(supid));
					}
			break;
		case "load":
			ArrayList<Student> st = null;
		
			 if(!id.equals("") ) {
					st=stDAO.getStudent(Integer.parseInt(id));
					}
			 pw.print(st.get(0).getName());
			break;
		
		}
	}
	
	public void teacher(String button, String id, String name, String address,PrintWriter pw) {
	
		TeacherDAOImp techDAO = new TeacherDAOImp();
		
		switch(button) {
		
		case "add":
			

			if(!name.equals("") && !id.equals("") && !address.equals("")) {
			Teacher teach = new Teacher(Integer.parseInt(id),name, address);
			techDAO.add(teach);
			}
			break;
		case "remove":
			
			 if(!id.equals("") ) {
				 techDAO.remove(Integer.parseInt(id));
					}
			break;
		case "update":

			 if(!name.equals("") && !id.equals("") && !address.equals("")) {
					
				 techDAO.Update(Integer.parseInt(id), name, address);
					}
			break;
		case "load":
			ArrayList<Teacher> teach = null;
		
			 if(!id.equals("") ) {
					teach=techDAO.getTeacher(Integer.parseInt(id));
					}
			 pw.print(teach.get(0).getName());
			break;
		
		}
	}
}
