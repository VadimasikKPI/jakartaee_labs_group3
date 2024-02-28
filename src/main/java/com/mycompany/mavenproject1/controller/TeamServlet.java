package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.model.Team;
import com.mycompany.mavenproject1.service.TeamService;
import jakarta.ejb.EJB;
//import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Teams servlet", value = "/team")
public class TeamServlet  extends HttpServlet {
    @EJB
    private TeamService teamService;

    private void processGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teamName = request.getParameter("teamName");
        if(teamName!= null){
//            Team team = teamService.getTeamByName(new TeamDTO(teamName));
//            request.setAttribute("teamRecords", team);
//            List<Team> teamList = teamService.findAll();
//            request.setAttribute("teamRecords", teamList);
        }
        else {
            List<Team> teamList = teamService.findAll();
            request.setAttribute("teamRecords", teamList);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/team.jsp");
        dispatcher.forward(request, response);
    }

    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processGetRequest(request, response);
    }


//    public TeamServlet() {
//        this.teamService = new TeamService();
//    }
}
