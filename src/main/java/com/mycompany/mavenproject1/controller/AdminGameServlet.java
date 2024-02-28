package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.model.Game;
import com.mycompany.mavenproject1.service.GameService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "Admin game servlet", value = "/admin_game")
public class AdminGameServlet extends HttpServlet {
    @EJB
    private GameService gameService;

//    public AdminGameServlet() {
//        this.gameService = new GameService();
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameAction = request.getParameter("action");
        if(nameAction != null && nameAction.equals("delete")){
            doDelete(request, response);
            return;
        }
        if(nameAction != null && nameAction.equals("update")){
            doPut(request, response);
            return;
        }
        String gameDate = request.getParameter("gameDate");
        String team1Id = request.getParameter("team1");
        String team2Id = request.getParameter("team2");
        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(gameDate);
            if (gameDate != null) {
                gameService.saveGame(parsedDate, team1Id, team2Id);
            }
            response.sendRedirect("/mavenproject1/admin_game");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//        if (gameDate != null) {
//            gameService.saveGame(parsedDate, team1Id, team2Id);
//        }
//        response.sendRedirect("/admin_team");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null || !username.equals("admin")) {
            response.sendRedirect("/mavenproject1/login");
            return;
        }
        List<Game> gameList = gameService.findAll();
        request.setAttribute("gameRecords", gameList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_game.jsp");
        dispatcher.forward(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("id = " + id);
        gameService.deleteGame(Long.parseLong(id));
        response.sendRedirect("/mavenproject1/admin_game");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("id1 = " + id);
        String newDate = request.getParameter("newDate");
        String team1Id = request.getParameter("team1");
        String team2Id = request.getParameter("team2");
        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDate);
            gameService.updateGame(Long.parseLong(id), parsedDate, team1Id, team2Id);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //gameService.updateGame(Long.parseLong(id), newDate, team1Id, team2Id);
        response.sendRedirect("/mavenproject1/admin_game");
    }

}
