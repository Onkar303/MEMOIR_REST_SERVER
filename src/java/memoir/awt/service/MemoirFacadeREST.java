/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoir.awt.service;

import Model.Month;
import Model.Postcode;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import memoir.awt.Cinema;
import memoir.awt.Memoir;

/**
 *
 * @author onkar
 */
@Stateless
@Path("memoir.awt.memoir")
public class MemoirFacadeREST extends AbstractFacade<Memoir> {

    @PersistenceContext(unitName = "Assignment1_memoirPU")
    private EntityManager em;

    public MemoirFacadeREST() {
        super(Memoir.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Memoir entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Memoir entity) {
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
    public Memoir find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoir> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoir> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("Memoir.findByMovieName/{movieName}")
    @Produces({"application/json"})
    public List<Memoir> findByMovieName(@PathParam("movieName") String moviename) {
        Query q = em.createNamedQuery("Memoir.findByMovieName");
        q.setParameter("movieName", moviename);
        return q.getResultList();
    }

    @GET
    @Path("Memoir.findByMovieReleaseDate/{movieReleaseDate}")
    @Produces({"application/json"})
    public List<Memoir> findByMovieReleaseDate(@PathParam("movieReleaseDate") String movieReleaseDate) {
        Query q = em.createNamedQuery("Memoir.findByMovieReleaseDate");
        try
        {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(movieReleaseDate);
            q.setParameter("movieReleaseDate", date);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return q.getResultList();
    }

    @GET
    @Path("Memoir.findByMovieWatchTime/{movieWatchTime}")
    @Produces({"application/json"})
    public List<Memoir> findByWatchTime(@PathParam("movieWatchTime") String movieWatchTime) {
        Query q = em.createNamedQuery("Memoir.findByMovieWatchTime");
        try
        {
          java.util.Date date = new SimpleDateFormat("HH:mm:ss").parse(movieWatchTime);
          q.setParameter("movieWatchTime",date,TemporalType.TIME);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return q.getResultList();

    }

    @GET
    @Path("Memoir.findByMovieWatchDate/{movieWatchDate}")
    @Produces({"application/json"})
    public List<Memoir> findByMovieWatchDate(@PathParam("movieWatchDate") String movieWatchDate) {
        Query query = em.createNamedQuery("Memoir.findByMovieWatchDate");
        try
        {
          Date date = new SimpleDateFormat("dd-MM-yyyy").parse(movieWatchDate);
          query.setParameter("movieWatchDate", date,TemporalType.DATE);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return query.getResultList();
    }

    @GET
    @Path("Memoir.findByMovieComment/{movieComment}")
    @Produces({"application/json"})
    public List<Memoir> findByMovieComment(@PathParam("movieComment") String movieComment) {
        Query query = em.createNamedQuery("Memoir.findByMovieComment");
        query.setParameter("movieComment", movieComment);
        return query.getResultList();
    }

    //task 3 part c dynamic query
    @GET
    @Path("findByCinemaNameAndMovieNameDynamic/{movieName}/{cinemaName}")
    @Produces({"application/json"})
    public List<Memoir> findByCinemaNameAndMovieNameDynamic(@PathParam("cinemaName") String cinemaName, @PathParam("movieName") String movieName) {
        TypedQuery<Memoir> query = em.createQuery("select m from Memoir m where m.cinemaId.cinemaName = :cinemaName and m.movieName = :movieName", Memoir.class);
        query.setParameter("cinemaName", cinemaName);
        query.setParameter("movieName", movieName);
        return query.getResultList();
    }

    //task 3 part d static query
    @GET
    @Path("Memoir.findByCinemaNameAndMovieNameStatic/{cinemaName}/{movieName}")
    @Produces({"application/json"})
    public List<Memoir> findByCinemaNameAndMovieNameStatic(@PathParam("cinemaName") String cinemaName, @PathParam("movieName") String movieName) {
        Query query = em.createNamedQuery("Memoir.findByCinemaNameAndMovieNameStatic");
        query.setParameter("cinemaName", cinemaName);
        query.setParameter("movieName", movieName);
        return query.getResultList();

    }

    //tak 4 part a
    @GET
    @Path("findTotalCountForEachCinema/{personId}/{fromDate},{toDate}")
    @Produces({"application/json"})
    public Object findTotalCountForEachCinema(@PathParam("personId") Integer personId, @PathParam("fromDate") Date fromDate, @PathParam("toDate") Date toDate) {
        
        List<Postcode> list = initAndAddPostcode();
        
        List<Object[]> q = em.createQuery("select substring(m.cinemaId.location,length(m.cinemaId.location)-3), count(m) from Memoir as m where m.personId.personId = '" + personId + "' and m.movieWatchDate between '" + fromDate + "' and '" + toDate + "' group by substring(m.cinemaId.location,length(m.cinemaId.location)-3)", Object[].class).getResultList();
        //List<Object[]> q = em.createQuery("select substring(m.cinemaId.location,length(m.cinemaId.location)-3) from Memoir as m where m.personId.personId = '" + personId + "' and m.movieWatchDate between '" + fromDate + "' and '" + toDate + "'", Object[].class).getResultList();

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        
        for(Postcode pObj:list)
        {
            for(Object[] row:q)
            {
                if(pObj.getPostcode() == Integer.parseInt(row[0].toString()))
                {
                    pObj.setCount(Integer.parseInt(row[1].toString()));
                }
            }
        }
        
        for (Postcode pObj : list) {
            JsonObject obj = Json.createObjectBuilder()
                    .add("location", String.valueOf(pObj.getPostcode()))
                    .add("count", String.valueOf(pObj.getCount()))
                    .build();

            arrayBuilder.add(obj);
        }

        JsonArray array = arrayBuilder.build();

        return array;

    }

    //task 4 part b
    @GET
    @Path("findCountPerMonth/{personId}/{year}")
    @Produces({"application/json"})
    public Object findCountPerMonth(@PathParam("personId") Integer personId, @PathParam("year") Integer year) {

        List<Month> months = initAndAddMonths();

        List<Object[]> query = em.createQuery("select extract(month from m.movieReleaseDate),count(m) from Memoir as m where m.personId.personId = '" + personId + "' and extract(year from m.movieReleaseDate) = '" + year + "' group by extract(month from m.movieReleaseDate),m.personId", Object[].class).getResultList();

        JsonArrayBuilder builder = Json.createArrayBuilder();

        for(int i = 0; i < 2 ;i++)
        {
            for(int j = 0;j<12;j++)
            {
             
             if(months.get(j).getMonthNumber().equals(query.get(i)[0].toString()))
             {
                months.get(j).setCount(Integer.parseInt(query.get(i)[1].toString()));
             }
             
            }
        }
        
        
        for (Month month : months) {
            JsonObject obj = Json.createObjectBuilder()
                    .add("month",month.getName())
                    .add("moviesWatched", month.getCount())
                    .build();

            builder.add(obj);
        }

        return builder.build();
    }

    //task 4 part c
    @GET
    @Path("findRatingById/{personId}")
    @Produces({"application/json"})
    public Object findRatingById(@PathParam("personId") Integer personId) {
        List<Object[]> query = em.createQuery("select m.movieName,m.movieReleaseDate,m.movieRating.star from Memoir m where m.movieRating.star = (select max(m1.movieRating.star) from Memoir as m1 where m1.personId.personId = '" + personId + "') and m.personId.personId = '" + personId + "'", Object[].class).getResultList();

        JsonArrayBuilder builder = Json.createArrayBuilder();

        for (Object[] row : query) {
            JsonObject obj = Json.createObjectBuilder()
                    .add("movieName", row[0].toString())
                    .add("movieReleaseDate", row[1].toString())
                    .add("movieStarRating", row[2].toString())
                    .build();

            builder.add(obj);
        }

        return builder.build();
    }

    //task 4 part d
    @GET
    @Path("findMoviesBySameReleaseYear/{personId}")
    @Produces({"application/json"})
    public Object findMoviesBySameReleaseYear(@PathParam("personId") Integer personId) {
        List<Object[]> q = em.createQuery("select m.movieName,EXTRACT(year from m.movieReleaseDate) from Memoir as m where m.personId.personId = '" + personId + "' and EXTRACT(year from m.movieReleaseDate) = EXTRACT(year from m.movieWatchDate)", Object[].class).getResultList();

        //  List<Object[]> q = em.createQuery("select m.movieName, extract(year from m.movieReleaseDate ) from Memoir as m where m.personId.personId = '" + personId + "' ",Object[].class).getResultList();
        JsonArrayBuilder builder = Json.createArrayBuilder();

        for (Object[] row : q) {
            JsonObject obj = Json.createObjectBuilder()
                    .add("movieNames", row[0].toString())
                    .add("releaseYear", row[1].toString())
                    .build();

            builder.add(obj);
        }

        return builder.build();
    }

    //task 4 part e
    @GET
    @Path("findMoviesByRemake/{personId}")
    @Produces({"application/json"})
    public Object findMoviesByRemake(@PathParam("personId") Integer personId) {
        List<Object[]> q = em.createQuery("select distinct m.movieName,extract(year from m.movieReleaseDate) from Memoir as m join Memoir as m1 on m.movieName = m1.movieName and extract(year from m.movieReleaseDate) != extract(year from m1.movieReleaseDate) where m.personId.personId = '" + personId + "' and m.movieName = m1.movieName", Object[].class).getResultList();
        JsonArrayBuilder builder = Json.createArrayBuilder();

        for (Object[] row : q) {
            JsonObject obj = Json.createObjectBuilder()
                    .add("movieNames", row[0].toString())
                    .add("releaseYears", row[1].toString())
                    .build();

            builder.add(obj);
        }

        return builder.build();
    }

    //task 4 part f
    @GET
    @Path("findTopFiveMoviesById/{personId}")
    @Produces({"application/json"})
    public Object findTOpFiveMoviesById(@PathParam("personId") Integer personId) {
        List<Object[]> q = em.createQuery("select m.movieName,m.movieReleaseDate,m.movieRating.star from Memoir as m where m.personId.personId = '" + personId + "' and extract(year from current_date) = extract (year from m.movieReleaseDate) order by m.movieRating.star desc", Object[].class).setMaxResults(5).getResultList();

        JsonArrayBuilder builder = Json.createArrayBuilder();

        for (Object[] row : q) {
            JsonObject obj = Json.createObjectBuilder()
                    .add("movieName", row[0].toString())
                    .add("movieReleaseDate", row[1].toString())
                    .add("movieStarRating", row[2].toString())
                    .build();

            builder.add(obj);
        }

        return builder.build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    private List<Month> initAndAddMonths() {

        List<Month> list = new ArrayList<>();

        list.add(new Month("1", "January", 0));
        list.add(new Month("2", "February", 0));
        list.add(new Month("3", "March", 0));
        list.add(new Month("4", "April", 0));
        list.add(new Month("5", "May", 0));
        list.add(new Month("6", "June", 0));
        list.add(new Month("7", "July", 0));
        list.add(new Month("8", "August", 0));
        list.add(new Month("9", "September", 0));
        list.add(new Month("10", "Octobar", 0));
        list.add(new Month("11", "November", 0));
        list.add(new Month("12", "December", 0));

        return list;
    }
    
    private List<Postcode> initAndAddPostcode(){
        
        List<Object> q = em.createQuery("select distinct substring(m.cinemaId.location,length(m.cinemaId.location)-3) from Memoir m",Object.class).getResultList();
        
        List<Postcode> list = new ArrayList<>();
        
        for(Object row: q)
        {
            list.add(new Postcode(Integer.parseInt(row.toString()),0));
        }
        
        return list;
    } 

}
