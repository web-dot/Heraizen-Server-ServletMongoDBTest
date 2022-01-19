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
        
        if(uri.contains("/getPlayersByTeam.do")) {
            IplStatService statService = IplStatServiceImpl.getInstance();
            List<Team> team = statService.getPlayersByTeam();
            request.setAttribute("teams", team.toString());
            rd = request.getRequestDispatcher("Results.jsp");
            rd.forward(request, response);
        }
        
        if(uri.contains("/getTeamInfoByLabel.do")) {
            IplStatService statService = IplStatServiceImpl.getInstance();
            statService.teamInfoByLabel();
        }
        
        if(uri.contains("/searchTeamInDB.do")) {
            IplStatService statService = IplStatServiceImpl.getInstance();
            boolean result = statService.searchTeam();
            System.out.println(result);
        }
        
        if(uri.contains("/testLabel.do")) {
            IplStatService statService = IplStatServiceImpl.getInstance();
            statService.testLabel();
        }
        
        if(uri.contains("/getPlayersOnLabel.do")) {
            IplStatService statService = IplStatServiceImpl.getInstance();
            statService.getPlayers();
        }
        
        if(uri.contains("/getPlayersBasedOnCity.do")) {
            System.out.println("getPlayersBasedOnCity");
            IplStatService statService = IplStatServiceImpl.getInstance();
            List<String> players = statService.getPlayersBasedOnCity();
            
        }
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    process(request, response);
		
	}

}
