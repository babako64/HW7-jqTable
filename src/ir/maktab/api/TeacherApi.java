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

import ir.maktab.DAO.TeacherDAOImp;
import ir.maktab.model.Teacher;

@Path("/teacher")
public class TeacherApi {

TeacherDAOImp teDAO = new TeacherDAOImp();
	
/**
 * Insert new teacher to Database
 * @param teach
 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addStudent(Teacher teach) {
		
		teDAO.add(teach);
	}
	
	/**
	 * Delete one teacher from Database
	 * @param stid
	 */
	
	@DELETE
	@Path("/{stid}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("stid") String stid) {
		
		teDAO.remove(Integer.parseInt(stid));
		
	}
	
	/**
	 * 
	 * Delete All teachers from Database
	 */
	
	@DELETE
	public void delete() {
		
		teDAO.removeAll();
		
	}
	
	/**
	 * Update teacher Data
	 * 
	 * @param teach
	 * @param id
	 */
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Teacher teach,@PathParam("id") String id) {
		
		teDAO.Update(teach.getId(), teach.getName(), teach.getAddress());
		
	}
	
	/**
	 * return teacher
	 * 
	 * @param id
	 * @return
	 */
	
	@GET
	//@Path("/{id}")
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Teacher> get(@QueryParam("id") String id) {
		
		ArrayList<Teacher> stList = new ArrayList<>();
		Teacher teach;
		stList = teDAO.getTeacher(Integer.parseInt(id));
		teach = stList.get(0);
		System.out.println(teach.getId() + teach.getName() + teach.getAddress());
		return stList;
	}
	
	/**
	 * return All teachers
	 * 
	 * @return
	 */
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Teacher> get() {
		ArrayList<Teacher> stList = new ArrayList<>();
		Teacher teach;
		stList =teDAO.getAll();
		return stList;
	}
}
