/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoir.awt.service;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import memoir.awt.Credentials;
import memoir.awt.Person;

/**
 *
 * @author onkar
 */
@Stateless
@Path("memoir.awt.person")
public class PersonFacadeREST extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "Assignment1_memoirPU")
    private EntityManager em;

    public PersonFacadeREST() {
        super(Person.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Person entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Person entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Person find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    //Named Queries
    @GET
    @Path("Person.findByName/{name}")
    @Produces({"application/json"})
    public List<Person> findByName(@PathParam("name") String name) {
        Query query = em.createNamedQuery("Person.findByName");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @GET
    @Path("Person.findBySurname/{surname}")
    @Produces({"application/json"})
    public List<Person> findBySurname(@PathParam("surname") String surname) {
        Query query = em.createNamedQuery("Person.findBySurname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @GET
    @Path("Person.findByGender/{gender}")
    @Produces({"application/json"})
    public List<Person> findByGender(@PathParam("gender") String gender) {
        Query query = em.createNamedQuery("Person.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }

    @GET
    @Path("Person.findByDob/{dob}")
    @Produces({"application/json"})
    public List<Person> findByDob(@PathParam("dob") String dob) {
        Query query = em.createNamedQuery("Person.findByDob");
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dob);
            query.setParameter("dob", date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return query.getResultList();
    }

    @GET
    @Path("Person.findByAddress/{address}")
    @Produces({"application/json"})
    public List<Person> findByAddress(@PathParam("address") String address) {
        Query query = em.createNamedQuery("Person.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }

    //dynamic task3 part b
    @GET
    @Path("findByThreeAttrsDyn/{name}/{surname}/{gender}")
    @Produces({"application/json"})
    public List<Person> findByThreeAttrsDyn(@PathParam("name") String name, @PathParam("surname") String surname, @PathParam("gender") String gender) {
        TypedQuery<Person> q = em.createQuery("select p from Person p where p.name = :name and p.surname=:surname and p.gender=:gender", Person.class);
        q.setParameter("name", name);
        q.setParameter("surname", surname);
        q.setParameter("gender", gender);
        return q.getResultList();
    }

    //signup api
    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Path("addNewUser/{name}/{surname}/{gender}/{postcode}/{address}/{state}/{DOB}/{username}/{password}")
    @Produces({"application/json"})
    public Object addNewUser(@PathParam("name") String name, @PathParam("surname") String surname, @PathParam("gender") String gender, @PathParam("postcode") String postcode, @PathParam("address") String address, @PathParam("state") String state, @PathParam("DOB") String DOB,@PathParam("username")String username,@PathParam("password")String password) {

        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        Date currentdate = new Date();

        List<Credentials> credentiallist= em.createQuery("select c from Credentials c where c.username = '" + username+ "' AND c.password = '" + password +"'", Credentials.class).getResultList();
     
        List<Person> personlist = em.createQuery("select p from Person p", Person.class).getResultList();
        Person person = null;
        try {
            person = new Person();
            person.setPersonId(personlist.size() + 1);
            person.setName(name);
            person.setSurname(surname);
            person.setGender(gender);
            person.setAddress(address + " " + state + " " + postcode);
            person.setCredentialsId(credentiallist.get(0));
            person.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(DOB));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JsonObject object = null;
        if(em.contains(person))
        {
              object = Json.createObjectBuilder()
                    .add("isSuccessfull", false)
                    .add("message", "user Person already registered")
                    .build();
        }
        else
        {
              em.persist(person);
              object = Json.createObjectBuilder()
                    .add("isSuccessfull", true)
                    .add("message", "person registration successfull")
                    .build();
        }
      
        return object;
    }

    @GET
    @Path("getPersonByCredentialId/{credentialsId}")
    @Produces({"application/json"})
    public Object getPersonByCredentialId(@PathParam("credentialsId") Integer credentailsId) {
        List<Person> q = em.createQuery("select p from Person p where p.personId = '" + credentailsId + "'", Person.class).getResultList();

        JsonArrayBuilder builder = Json.createArrayBuilder();

        for (Person person : q) {
            JsonObject object = Json.createObjectBuilder()
                    .add("id", person.getPersonId())
                    .add("name", person.getName())
                    .add("surname", person.getSurname())
                    .add("gender", person.getGender())
                    .add("address", person.getAddress())
                    .build();

            builder.add(object);

        }

        JsonObject object = Json.createObjectBuilder()
                .add("person", builder)
                .build();
        return object;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
