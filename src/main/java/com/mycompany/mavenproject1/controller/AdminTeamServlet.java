package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.model.Team;
import com.mycompany.mavenproject1.service.TeamService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "Admin team servlet", value = "/admin_team")
public class AdminTeamServlet extends HttpServlet {
    @EJB
    private TeamService teamService;

//    public AdminTeamServlet() {
//        this.teamService = new TeamService();
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teamName = request.getParameter("teamName");
        String nameAction = request.getParameter("action");
        if(nameAction != null && nameAction.equals("delete")){
            doDelete(request, response);
            return;
        }
        if(nameAction != null && nameAction.equals("update")){
            doPut(request, response);
            return;
        }

        if (teamName != null) {
            teamService.saveTeam(teamName);
        }
        response.sendRedirect("/mavenproject1/admin_team");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Перевірка прав доступу
        String username = (String) request.getSession().getAttribute("username");
        if (username == null || !username.equals("admin")) {
            response.sendRedirect("/mavenproject1/login");
            return;
        }
        List<Team> teamList = teamService.findAll();
        request.setAttribute("teamRecords", teamList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_team.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("id = " + id);
        teamService.deleteTeam(Long.parseLong(id));
        response.sendRedirect("/mavenproject1/admin_team");
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("id1 = " + id);
        String newName = request.getParameter("newName");
        teamService.updateTeamById(Long.parseLong(id), newName);
        response.sendRedirect("/mavenproject1/admin_team");
    }
}
