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
import javax.ws.rs.core.MediaType;

import ir.maktab.DAO.TeacherDAOImp;
import ir.maktab.model.Teacher;

@Path("/teacher")
public class TeacherApi {

TeacherDAOImp teDAO = new TeacherDAOImp();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addStudent(Teacher teach) {
		
		teDAO.add(teach);
	}
	
	@DELETE
	@Path("/{stid}")
	public void delete(@PathParam("stid") String stid) {
		
		teDAO.remove(Integer.parseInt(stid));
		
	}
	
	@DELETE
	public void delete() {
		
		teDAO.removeAll();
		
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Teacher teach,@PathParam("id") String id) {
		
		teDAO.Update(teach.getId(), teach.getName(), teach.getAddress());
		
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Teacher> get(@PathParam("id") String id) {
		
		ArrayList<Teacher> stList = new ArrayList<>();
		Teacher teach;
		stList = teDAO.getTeacher(Integer.parseInt(id));
		teach = stList.get(0);
		System.out.println(teach.getId() + teach.getName() + teach.getAddress());
		return stList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Teacher> get() {
		ArrayList<Teacher> stList = new ArrayList<>();
		Teacher teach;
		stList =teDAO.getAll();
		return stList;
	}
}
