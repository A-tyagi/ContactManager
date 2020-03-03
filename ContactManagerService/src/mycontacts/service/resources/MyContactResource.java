package mycontacts.service.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mycontacts.dao.ContactsDAOInterface;
//import mycontacts.model.BusinessContact;
import mycontacts.model.Contact;
//import mycontacts.model.FriendContact;
//import mycontacts.model.WorkContact;

@Path("/contacts")
public class MyContactResource {
	@Context ServletContext context;
	ContactsDAOInterface contactsDAO;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Contact newContact, @Context HttpServletRequest request) throws Exception {
		contactsDAO = (ContactsDAOInterface) context.getAttribute("ContactsDAO");
		long newProductId = contactsDAO.addContact(newContact);
		URI uri = new URI(request.getRequestURL().toString() + "/" + newProductId);
		return Response.created(uri).entity(Long.valueOf(newProductId)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> list() throws Exception {
		contactsDAO = (ContactsDAOInterface) context.getAttribute("ContactsDAO");
		return contactsDAO.getContactsList();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") long id) throws Exception {
		contactsDAO = (ContactsDAOInterface) context.getAttribute("ContactsDAO");
		Contact contact = contactsDAO.getContact(id);
		if (contact != null) {
			return Response.ok(contact, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, Contact contact) {
		contactsDAO = (ContactsDAOInterface) context.getAttribute("ContactsDAO");
		contact.setId(id);
		try {
			contactsDAO.updateContact(contact); 
			return Response.ok().build();
		} catch (Exception e) {
			return Response.notModified().build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		contactsDAO = (ContactsDAOInterface) context.getAttribute("ContactsDAO");
		try  {
			contactsDAO.deleteContact(id);
			return Response.ok().build();	
		} catch (Exception e) {
			return Response.notModified().build();
		}
	}
}