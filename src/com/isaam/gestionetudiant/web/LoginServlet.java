package com.isaam.gestionetudiant.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import com.isaam.gestionetduaint.dao.EtudaintDao;
import com.isaam.gestionetudiant.model.Etudiant;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EtudaintDao etudiantDao;
	
	  private ServletConfig config;

	  //Setting JSP page

	  String page="gestionEtudiant.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        this.etudiantDao=new EtudaintDao();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		
		String login = request.getParameter("login");
        String password = request.getParameter("password");
        this.etudiantDao=new EtudaintDao();
      
        try {
            if (this.etudiantDao.loginuser(login, password)) {
            	 ArrayList<Etudiant> liste=new ArrayList<Etudiant>();
             	liste=this.etudiantDao.getAllEtudiant();
             	if(liste!=null) {
             		System.out.println("count list etudiant \t "+liste.size()+"1 etudiant"+liste.get(0).toString());
             	}
             	else {
             		System.out.println("probeleme de chargement");

             	}
             	// request.setAttribute("data",liste);
             		request.setAttribute("liste", liste);
             	  //Disptching request
   				 request.setAttribute("nom", "");


             	  RequestDispatcher dispatcher = request.getRequestDispatcher("gestionEtudiant.jsp");

             	  if (dispatcher != null){

             	  dispatcher.forward(request, response);

             	  } 
                //response.sendRedirect("gestionEtudiant.jsp");

            } else {
                HttpSession session = request.getSession();
                
                System.out.print("erreur de authentification");
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("echec.jsp");

           	  if (dispatcher != null){

           	  dispatcher.forward(request, response);

           	  } 
                //session.setAttribute("user", username);
                //response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		
		doGet(request, response);
	}

}
