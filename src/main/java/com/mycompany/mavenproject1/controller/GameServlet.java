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
import java.util.List;

@WebServlet(name = "Games servlet", value = "/game")
public class GameServlet extends HttpServlet {

    @EJB
    private GameService gameService;

    private void processGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teamName = request.getParameter("teamName");
        if(teamName!= null){

        }
        else {
            List<Game> gameList = gameService.findAll();
            request.setAttribute("gameRecords", gameList);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/game.jsp");
        dispatcher.forward(request, response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processGetRequest(request, response);
    }
}
