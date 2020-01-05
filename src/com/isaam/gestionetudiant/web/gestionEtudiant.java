package com.isaam.gestionetudiant.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isaam.gestionetduaint.dao.EtudaintDao;
import com.isaam.gestionetudiant.model.Etudiant;

/**
 * Servlet implementation class gestionEtudiant
 */
@WebServlet("/gestionEtd")
public class gestionEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EtudaintDao etudaintDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionEtudiant() {
        super();
        this.etudaintDao=new EtudaintDao();

        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	/*
	 
	 List<Etudiant> liste=new ArrayList<Etudiant>();
            	liste=this.etudiantDao.getAllEtudiant();
            	if(liste!=null) {
            		System.out.println("count list etudiant \t "+liste.size()+"1 etudiant"+liste.get(0).toString());
            	}
            	else {
            		System.out.println("probeleme de chargement");

            	}
            	 request.setAttribute("data",liste);

            	  //Disptching request

            	  RequestDispatcher dispatcher = request.getRequestDispatcher(page);

            	  if (dispatcher != null){

            	  dispatcher.forward(request, response);

            	  } 
	 
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int id=Integer.valueOf(request.getParameter("search"));

		   // List<Etudiant> liste=(List<Etudiant>) request.getAttribute("data");  
		    //out.print("Welcome "+liste.get(0).toString());
		 List<Etudiant> liste=new ArrayList<Etudiant>();
		try {
			if(etudaintDao.getEtudiantById(id)!=null)
			{
				liste.add(etudaintDao.getEtudiantById(id));
				 request.setAttribute("liste", liste);
				    request.setAttribute("nom", "");
				 	  RequestDispatcher dispatcher = request.getRequestDispatcher("gestionEtudiant.jsp");

	             	  if (dispatcher != null){

	             	  dispatcher.forward(request, response);

	             	  } 
			}
			else {
				liste=this.etudaintDao.getAllEtudiant();
				request.setAttribute("liste", liste);
			    request.setAttribute("nom", "");
			 	  RequestDispatcher dispatcher = request.getRequestDispatcher("gestionEtudiant.jsp");

             	  if (dispatcher != null){

             	  dispatcher.forward(request, response);

             	  } 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
