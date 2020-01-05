package com.isaam.gestionetudiant.conexion;
import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.DriverManager;
public class DB_Conexion {

	
	 public  Connection conn;
	    public Statement statement;
	    public static DB_Conexion db;
	    private DB_Conexion() {
	        String url= "jdbc:mysql://localhost:3306/";
	        String dbName = "gestionetudiant";
	        String driver = "com.mysql.jdbc.Driver";
	        String userName = "root";
	        String password = "";
	        try {
	            Class.forName(driver).newInstance();
	          conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
	        }
	        catch (Exception sqle) {
	            sqle.printStackTrace();
	        }
	    }
	    
	    public static synchronized DB_Conexion getDbCon() {
	        if ( db == null ) {
	            db = new DB_Conexion();
	        }
	        return db;
	 
	    }
	    
	    public ResultSet query(String query) throws SQLException{
	        statement = db.conn.createStatement();
	        ResultSet res = statement.executeQuery(query);
	        return res;
	    }
	    /**
	     * @desc Method to insert data to a table
	     * @param insertQuery String The Insert query
	     * @return boolean
	     * @throws SQLException
	     */
	    public int insert(String insertQuery) throws SQLException {
	        statement = db.conn.createStatement();
	        int result = statement.executeUpdate(insertQuery);
	        return result;
	 
	    }
	    
	    
	    
	    
	    
	    
}
