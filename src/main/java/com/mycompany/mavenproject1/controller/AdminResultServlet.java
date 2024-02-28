package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.model.Results;
import com.mycompany.mavenproject1.service.ResultService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Admin results servlet", value = "/admin_results")
public class AdminResultServlet extends HttpServlet {
    @EJB
    private ResultService resultService;

//    public AdminResultServlet() {
//        this.resultService = new ResultService();
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
        String gameId = request.getParameter("gameId");
        String score = request.getParameter("score");
        String winnerId = request.getParameter("winnerId");
        System.out.println("gameId = " + gameId);
        System.out.println("score = " + score);
        System.out.println("winnerId = " + winnerId);
        if (gameId != null) {
            resultService.saveResult(gameId, score, winnerId);
        }
        response.sendRedirect("/mavenproject1/admin_results");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null || !username.equals("admin")) {
            response.sendRedirect("/mavenproject1/login");
            return;
        }
        List<Results> resultsList = resultService.findAll();
        request.setAttribute("resultsRecords", resultsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_result.jsp");
        dispatcher.forward(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("id = " + id);
        resultService.deleteResult(Long.parseLong(id));
        response.sendRedirect("/mavenproject1/admin_results");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("id1 = " + id);
        String newGameId = request.getParameter("newGame");
        String score = request.getParameter("score");
        String winnerId = request.getParameter("winnerName");
        resultService.updateResult(Long.parseLong(id), newGameId, score, winnerId);

        //gameService.updateGame(Long.parseLong(id), newDate, team1Id, team2Id);
        response.sendRedirect("/mavenproject1/admin_results");
    }

}
