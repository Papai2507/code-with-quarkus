package org.sumit;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/persons")
public class PersonController {
	
	@Inject
	PersonRepository personRepository;
        
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons(){
    	return personRepository.listAll();
        
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person get(Long id) {
        return personRepository.findById(id);
    }
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getByName(String name) {
        return personRepository.findByName(name);
    }
}
