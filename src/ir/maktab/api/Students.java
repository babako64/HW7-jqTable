package ir.maktab.api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ir.maktab.DAO.StudentDAOImp;
import ir.maktab.model.Student;

@Path("/students")
public class Students {

StudentDAOImp stDAO = new StudentDAOImp();
	
/**
 * Insert new student to Database
 * @param teach
 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addStudent(Student st) {
		
		stDAO.add(st);
	}
	
	/**
	 * Delete one student from Database
	 * @param stid
	 */
	
	@DELETE
	@Path("/{stid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("stid") String stid) {
		System.out.println(stid);
		stDAO.remove(Integer.parseInt(stid));
		
	}
	
	/**
	 * 
	 * Delete All students from Database
	 */
	
	@DELETE
	public void delete() {
		
		stDAO.removeAll();
		
	}
	

	/**
	 * Update student Data
	 * 
	 * @param teach
	 * @param id
	 */
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Student st,@PathParam("id") String id) {
		
		stDAO.Update(st.getId(), st.getName(), st.getDept(), st.getSuperVisorId());
		
	}
	
	/**
	 * return student Data
	 * 
	 * @param id
	 * @return
	 */
	
	@GET
	//@Path("/{id}")
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public  ArrayList<Student> get(@QueryParam("id")String id) {
		
		ArrayList<Student> stList = new ArrayList<>();
		Student st;
		stList = stDAO.getStudent(Integer.parseInt(id));
		st = stList.get(0);
		System.out.println(st.getId() + st.getName() + st.getDept() + st.getSuperVisorId());
		return stList;
	}
	
	/**
	 * return All students
	 * 
	 * @return
	 */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Student> get() {
		ArrayList<Student> stList = new ArrayList<>();
		//Student st;
		stList =stDAO.getAll();
		return stList;
	}
}
