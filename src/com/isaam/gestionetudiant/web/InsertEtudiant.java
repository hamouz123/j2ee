package com.isaam.gestionetudiant.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import com.isaam.gestionetduaint.dao.EtudaintDao;
import com.isaam.gestionetudiant.model.Etudiant;

/**
 * Servlet implementation class InsertEtudiant
 */
@WebServlet("/InsertEtudiant")
public class InsertEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EtudaintDao etudaintDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertEtudiant() {
		super();
		this.etudaintDao = new EtudaintDao();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.etudaintDao=new EtudaintDao();
		
		
		String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        Float moyenne= Float.valueOf(request.getParameter("moyenne"));
        String login= request.getParameter("login");
        String password= request.getParameter("password");
        String validation= request.getParameter("validation");
        ArrayList<Etudiant> liste=new ArrayList<Etudiant>();
     	
        try {
			if(this.etudaintDao.insertEtudiant(new Etudiant(nom, prenom, moyenne, login, password) )>0)
			{
				System.out.println("Ajout avec suceess de l'etudiant \t "+nom);
	    
	             liste=this.etudaintDao.getAllEtudiant();
	             
	             //System.out.println("count list etudiant \t "+liste.size()+"1 etudiant"+liste.get(0).toString());

	             	// request.setAttribute("data",liste);
	             request.setAttribute("liste", liste);
				 request.setAttribute("nom", nom);
			 	  RequestDispatcher dispatcher = request.getRequestDispatcher("gestionEtudiant.jsp");

             	  if (dispatcher != null){

             	  dispatcher.forward(request, response);

             	  } 
			}
			else
			{
			    liste=this.etudaintDao.getAllEtudiant();
             	// request.setAttribute("data",liste);
             request.setAttribute("liste", liste);
			 request.setAttribute("nom", nom);
		 	  RequestDispatcher dispatcher = request.getRequestDispatcher("gestionEtudiant.jsp");

         	  if (dispatcher != null){

         	  dispatcher.forward(request, response);

         	  } 
			}
		
        }catch (SQLException e) {
			System.out.println("probleme d'Ajout  l'etudiant \t "+nom);
			PrintWriter out = response.getWriter();
			out.print("Probleme d'ajoute  ");
			e.printStackTrace();
		}


        
		doGet(request, response);
	}

}
