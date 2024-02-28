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

@WebServlet(name = "Results servlet", value = "/results")

public class ResultServlet extends HttpServlet {
    @EJB
    private ResultService resultService;

//    public ResultServlet() {
//        this.resultService = new ResultService();
//    }

    private void processGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teamName = request.getParameter("teamName");
        if(teamName!= null){

        }
        else {
            List<Results> resultsList = resultService.findAll();
            request.setAttribute("resultsRecords", resultsList);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/results.jsp");
        dispatcher.forward(request, response);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processGetRequest(request, response);
    }
}
