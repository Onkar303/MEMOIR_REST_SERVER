/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoir.awt.service;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
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
import memoir.awt.Cinema;
import memoir.awt.Memoir;

/**
 *
 * @author onkar
 */
@Stateless
@Path("memoir.awt.cinema")
public class CinemaFacadeREST extends AbstractFacade<Cinema> {

    @PersistenceContext(unitName = "Assignment1_memoirPU")
    private EntityManager em;

    public CinemaFacadeREST() {
        super(Cinema.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cinema entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cinema entity) {
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
    public Cinema find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cinema> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cinema> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("Cinema.findByCinemaName/{cinemaName}")
    @Produces({"application/json"})
    public List<Cinema> findByCinemaName(@PathParam("cinemaName") String cinemaName) {
        Query query = em.createNamedQuery("Cinema.findByCinemaName");
        query.setParameter("cinemaName", cinemaName);
        return query.getResultList();
    }

    @GET
    @Path("Cinema.findByLocation/{location}")
    @Produces({"application/json"})
    public List<Cinema> findByLocation(@PathParam("location") String location) {
        Query query = em.createNamedQuery("Cinema.findByLocation");
        query.setParameter("location", location);
        return query.getResultList();
    }

    @GET
    @Path("getAllCinemas")
    @Produces({"application/json"})
    public Object getAllCinemas() {

        List<Cinema> q = em.createQuery("select c from Cinema c", Cinema.class).getResultList();

        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Cinema row : q) {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("cinemaId", row.getCinemaId().toString())
                    .add("cinemaName", row.getCinemaName().toString())
                    .add("cinemaLocation", row.getLocation().toString())
                    .build();
            builder.add(jsonObject);
        }

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("cinemas", builder.build())
                .build();

        return jsonObject;
    }

    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Path("addCinema/{cinemaName}/{cinemaLocation}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object addCinema(@PathParam("cinemaName") String cinemaName, @PathParam("cinemaLocation") String cinemaLocation) {

        List<Cinema> qC = em.createQuery("select c from Cinema c", Cinema.class).getResultList();
        List<Memoir> qM = em.createQuery("select m from Memoir m", Memoir.class).getResultList();
        Cinema cinema = new Cinema();

        cinema.setCinemaId(qC.size() + 1);
        cinema.setCinemaName(cinemaName);
        cinema.setLocation(cinemaLocation);
        cinema.setMemoirCollection(qM);

        JsonObject obj = null;
        if (em.contains(cinema)) {
            obj = Json.createObjectBuilder()
                    .add("isSuccessfull", false)
                    .add("message", "database already constains the data")
                    .build();
        } else {
            em.persist(cinema);
            obj = Json.createObjectBuilder()
                    .add("isSuccessfull", true)
                    .add("message", "insertion successfull")
                    .build();
        }

        return obj;
    }
//    //task 3 d static query
//    @GET
//    @Path("Cinema.findByImplicitjoin/{}/{}")
//    @Produces({"application/json"})
//    public List<Cinema> findByImplicitJoin(@PathParam("")String obj,@PathParam("")String obj1)
//    {
//        return null;
//    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
