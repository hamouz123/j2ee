package com.isaam.gestionetudiant.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isaam.gestionetduaint.dao.EtudaintDao;
import com.isaam.gestionetudiant.model.Etudiant;

/**
 * Servlet implementation class SupprimerEtudiant
 */
@WebServlet("/SupprimerEtudiant")
public class SupprimerEtudiant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EtudaintDao etudaintDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerEtudiant() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.etudaintDao=new EtudaintDao();
		int id=Integer.valueOf(request.getParameter("supEtd"));
	    ArrayList<Etudiant> liste=new ArrayList<Etudiant>();
		try {
			if(etudaintDao.deleteEtudiant(id)>0)
			{
				System.out.println("succees suprimmer");
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
			System.out.println("probeleme de supression de l'etudiant");

			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}
