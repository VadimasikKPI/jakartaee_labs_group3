package com.mycompany.mavenproject1.resources;

import com.mycompany.mavenproject1.model.Team;
import com.mycompany.mavenproject1.service.TeamService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;


@Path("team")
public class TeamRest {
    @EJB
    private TeamService teamService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllTeams(@QueryParam("teamName") String teamName,
                                @QueryParam("start") int start,
                                @QueryParam("size") int size) {
        if(teamName != null){
            List<Team> teamList = teamService.getTeamsFilteredByName(teamName);
            return Response.ok().entity(teamList).build();
        }
        if(start >= 0 && size > 0 && start + size <= teamService.findAll().size()){
            List<Team> teamList = teamService.findAll();
            return Response.ok().entity(teamList.subList(start, start + size)).build();
        }
        List<Team> teamList = teamService.findAll();
        return Response.ok().entity(teamList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveTeam(@QueryParam("teamName") String teamName) {
        teamService.saveTeam(teamName);
        System.out.println(teamName);
        return Response.status(201).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTeam(@QueryParam("id") Long id, @QueryParam("newName") String newName) {
        teamService.updateTeamById(id, newName);
        return Response.ok().build();
    }

    @DELETE
    public Response deleteTeam(@QueryParam("id") Long id) {
        teamService.deleteTeam(id);
        return Response.ok().build();
    }
}
