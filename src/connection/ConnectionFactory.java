/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 *
 * @author breno
 */


import java.sql.*;


/**
 *
 * @author breno
 */
public class ConnectionFactory {
    
   static final String JDBC_DRIVER = "org.postgresql.Driver";  
   static final String DB_URL = "jdbc:postgresql://localhost:5432/world";

   //  Database credentials
   static final String USER = "postgres";
   static final String PASS = "nb3alita";
   
   
   public static Connection getConnection() {
       
       try {
           
           Class.forName(JDBC_DRIVER);
           
           return DriverManager.getConnection(DB_URL, USER, PASS);
                   
       } catch (ClassNotFoundException | SQLException ex) {
           throw new RuntimeException("Erro na conexão", ex);
       }
   }
   
   public static void closeConnection(Connection con) {
       if(con != null) {
           
           try {
               con.close();
           } catch (SQLException ex) {
               System.err.println("Erro: "+ex);
           }
       }
   }
   public static void closeConnection(Connection con, PreparedStatement stmt) {
       if(stmt != null) {
           
           try {
               stmt.close();
           } catch (SQLException ex) {
               System.err.println("Erro: "+ex);
           }
       }
       
       closeConnection(con);
   }
   public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
       if(rs != null) {
           
           try {
               rs.close();
           } catch (SQLException ex) {
               System.err.println("Erro: "+ex);
           }
       }
       
       closeConnection(con, stmt);
   }
}
