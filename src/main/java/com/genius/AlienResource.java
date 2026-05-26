package com.genius;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/aliens")
public class AlienResource {

    AlienRepository repo = new AlienRepository();
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Alien> getAliens(){
        System.out.println("getAliens called...");

        return repo.getAliens();
    }

    @POST
    @Path("/alien")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Alien createAlien(Alien a1){
        System.out.println(a1);
        repo.create(a1);
        return a1;
    }

    @GET
    @Path("/alien/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alien getAlien(@PathParam("id") int id){
        return repo.getAlien(id);
    }

    @PUT
    @Path("/alien/update")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Alien updateAlien(Alien a1){
        System.out.println(a1);
        if(repo.getAlien(a1.getId()).getId() == 0){
            repo.create(a1);
            return a1;
        }
        repo.update(a1);
        return a1;
    }

    @DELETE
    @Path("/alien/delete/{id}")
    @Consumes
    public Alien deleteAlien(@PathParam("id") int id){
        Alien alien = repo.getAlien(id);
        if(alien.getId()!=0){
            repo.delete(id);
        }
        return alien;
    }
}