package com.iplapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iplapp.domain.Team;
import com.iplapp.service.IplStatService;
import com.iplapp.service.IplStatServiceImpl;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        RequestDispatcher rd;
        System.out.println(uri);
        
        if(uri.contains("/getPlayersByTeam.do")) {
            IplStatService statService = IplStatServiceImpl.getInstance();
            List<Team> team = statService.getPlayersByTeam();
            request.setAttribute("teams", team.toString());
            rd = request.getRequestDispatcher("Results.jsp");
            rd.forward(request, response);
        }
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    process(request, response);
		
	}

}
