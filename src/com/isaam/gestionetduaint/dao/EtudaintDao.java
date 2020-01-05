package com.isaam.gestionetduaint.dao;

import com.isaam.gestionetudiant.conexion.DB_Conexion;
import com.isaam.gestionetudiant.model.Etudiant;
import com.mysql.jdbc.Connection;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


import com.isaam.gestionetudiant.model.*;

public class EtudaintDao {

	//private DB_Conexion db;
	 private Connection conn;
	 
	public EtudaintDao() {
	
	
	}
	
	
	
	// Login user
	public boolean loginuser(String login,String password)
	{
		boolean status=false;
		
		this.conn=DB_Conexion.getDbCon().conn;
		if(this.conn==null)
		{
			System.out.print("erre de conexion");
			
		}else
		{
	
		
			try (PreparedStatement preparedStatement = this.conn.prepareStatement("select * from user where login = ? and password = ? ")) {
			    preparedStatement.setString(1, login);
	            preparedStatement.setString(2, password);

	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            status = rs.next();     
			}
		
			catch(SQLException e) {
			 printSQLException(e);
		 
			 
			}
		
		}
		
		
		return status;
	}
	
	
	
	
	
	
	
	
	
	// Login Password method
	public boolean loginEtudiant(Etudiant etudiant)
	{
		boolean status=false;
		
		this.conn=DB_Conexion.getDbCon().conn;
		if(this.conn==null)
		{
			System.out.print("erre de conexion");
			
		}else
		{
	
		
			try (PreparedStatement preparedStatement = this.conn.prepareStatement("select * from etudiant where login = ? and password = ? ")) {
			    preparedStatement.setString(1, etudiant.getLogin());
	            preparedStatement.setString(2, etudiant.getPassword());

	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            status = rs.next();     
			}
		
			catch(SQLException e) {
			 printSQLException(e);
		 
			 
			}
		
		}
		
		
		return status;
	}
	
	
	//insert etudiant
	
	
	public int insertEtudiant(Etudiant etd)  throws SQLException
	{
		int rowsInserted=0;

		this.conn=DB_Conexion.getDbCon().conn;
		if(this.conn==null)
		{
			System.out.print("erre de conexion");
			
		}
		else {
		
		String sql = "INSERT INTO etudiant (nom, prenom, moyenne, login, password) VALUES (?, ?, ?, ?, ?)";
	

		PreparedStatement statement = this.conn.prepareStatement(sql);
		statement.setString(1, etd.getNom());
		statement.setString(2, etd.getPrenom());
		statement.setFloat(3, etd.getMoyenne());
		statement.setString(4, etd.getLogin());
		statement.setString(5, etd.getPassword());
		 
		rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("A new etduaint was inserted successfully!");
		}
		}
		return rowsInserted;
				
	}
	
	
	
	
	//update etudiant
	
	
	public int updateEtudiant(int id,Etudiant etd) throws SQLException
	{
		this.conn=DB_Conexion.getDbCon().conn;
		String sql = "UPDATE etudiant SET nom=?, prenom=?, moyenne=? WHERE id=?";

		PreparedStatement statement = conn.prepareStatement(sql);
		
		
		statement.setString(1, etd.getNom());
		statement.setString(2, etd.getPrenom());
		statement.setFloat(3, etd.getMoyenne());
		
		statement.setInt(4, id);
		int rowsUpdated=0;
		
		if(this.conn==null)
		{
			System.out.print("erre de conexion");
			
		}
		else {
			
			 rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
		}
		return rowsUpdated;
	}
	
	
	//delete etudiant
	public int deleteEtudiant(int id) throws SQLException {
		int rowsDeleted=0;
	
		this.conn=DB_Conexion.getDbCon().conn;
		String sql = "DELETE FROM etudiant WHERE id=?";
		 
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);	
		
	 rowsDeleted = statement.executeUpdate();
		if (rowsDeleted > 0) {
		    System.out.println("A etudiant was deleted successfully!");
		}
				
		return rowsDeleted;		
	}
	
	
	
	//get all etudiant
	
	public ArrayList<Etudiant>getAllEtudiant() throws SQLException{
		ArrayList<Etudiant>liste=new ArrayList<Etudiant>();
		
		this.conn=DB_Conexion.getDbCon().conn;
		Statement statement = conn.createStatement();
		
		String sql = "SELECT * FROM etudiant";
		
		if(this.conn==null)
		{
			System.out.print("erre de conexion");
			
		}else
		{
			try (ResultSet result = statement.executeQuery(sql);){
				int count = 0;
				 
				while (result.next()){
					int id=result.getInt("id");
				    String nom = result.getString("nom");
				    String prenom = result.getString("prenom");
				    Float moyenne = result.getFloat("moyenne");
				    String login = result.getString("login");
				    String password= result.getString("password");
				    
				    liste.add(new Etudiant(id,nom, prenom, moyenne, login, password));
				   // String output = "User #%d: %s - %s - %s - %s- %s";
				    System.out.println("res" +id+ nom+ prenom+ moyenne+password);
				}
			}
			catch(SQLException e) {
				 printSQLException(e);
			}
		}
		
		return liste;
	}
	//get Etudiant By Id
	
	public Etudiant getEtudiantById(int ide) throws SQLException{
		Etudiant e=new Etudiant();
		ArrayList<Etudiant>liste=new ArrayList<Etudiant>();
		liste=this.getAllEtudiant();
		for(int i=0;i<liste.size();i++)
		{
			if(liste.get(i).getId()==ide)
			{
				e=liste.get(i);
				break;
			}
		}

		return e;
	}
	
	
	
	//excpetion
	
	 private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
	
	
	
}
