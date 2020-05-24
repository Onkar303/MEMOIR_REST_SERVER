/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoir.awt.service;

import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
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

/**
 *
 * @author onkar
 */
@Stateless
@Path("memoir.awt.credentials")
public class CredentialsFacadeREST extends AbstractFacade<Credentials> {

    @PersistenceContext(unitName = "Assignment1_memoirPU")
    private EntityManager em;

    public CredentialsFacadeREST() {
        super(Credentials.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credentials entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Credentials entity) {
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
    public Credentials find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credentials> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credentials> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    //static methods
    @GET
    @Path("Credentials.findByUserName/{username}")
    @Produces({"application/json"})
    public List<Credentials> findByUserName(@PathParam("username") String username) {
        Query q = em.createNamedQuery("Credentials.findByUsername");
        q.setParameter("username", username);
        return q.getResultList();
    }

    @GET
    @Path("Credentials.findByPassword/{password}")
    @Produces({"application/json"})
    public List<Credentials> findByPassword(@PathParam("password") String password) {
        Query q = em.createNamedQuery("Credentials.findByPassword");
        q.setParameter("password", password);
        return q.getResultList();
    }

    @GET
    @Path("Credentials.findBySignupdate/{signupdate}")
    @Produces({"application/json"})
    public List<Credentials> findBySignupdate(@PathParam("signupdate") String signupdate) {
        Query q = em.createNamedQuery("Credentials.findBySignupdate");
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(signupdate);
            q.setParameter("signupdate", date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return q.getResultList();
    }

    //sign-in api
    @GET
    @Path("Credentials.userExists/{username}/{password}")
    @Produces({"application/json"})
    public Object userExists(@PathParam("username") String username, @PathParam("password") String password) {
        List<Credentials> q = em.createQuery("select c from Credentials c where c.username = '" + username + "' and c.password = '" + password + "'", Credentials.class).getResultList();

        boolean userExists = false;
        if (q.size() > 0) {
            userExists = true;
        }

        JsonObject jsonObject = null;
        if (userExists) {
            jsonObject = Json.createObjectBuilder()
                    .add("userExists", userExists)
                    .add("userId", q.get(0).getCredentialsId())
                    .add("userName", q.get(0).getUsername())
                    .add("userPassword", q.get(0).getPassword())
                    .build();
        } else {
            jsonObject = Json.createObjectBuilder()
                    .add("userExists", userExists)
                    .add("userId", "0")
                    .add("userName", "no user")
                    .add("userPassword", "no password")
                    .build();
        }

        return jsonObject;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
