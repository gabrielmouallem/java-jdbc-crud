/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.City;
import model.Country;
import model.Countrylanguage;
import model.CountrylanguageId;

/**
 *
 * @author breno
 */
public class CountryLanguageDAO {
    
    private Connection con = null;

    public CountryLanguageDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Countrylanguage cl) {
        
        String sql = "INSERT INTO countrylanguage (id, country, isofficial, percentage) VALUES (?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setObject(1, cl.getId());
            stmt.setObject(2, cl.getCountry());
            stmt.setBoolean(3, cl.isIsofficial());
            stmt.setFloat(4, cl.getPercentage());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Countrylanguage> findAll() {
        
        String sql = "SELECT * FROM countrylanguage";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Countrylanguage> cls = new ArrayList<>();
        
        try {
            
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                
                Countrylanguage cl = new Countrylanguage();
                
                cl.setId((CountrylanguageId) rs.getObject("id"));
                cl.setCountry((Country) rs.getObject("country"));
                cl.setIsofficial(rs.getBoolean("isofficial"));
                cl.setPercentage(rs.getFloat("percentage"));
                cls.add(cl);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return cls;
    }
    
    public boolean update(Countrylanguage cl) {
        
        String sql = "UPDATE countrylanguage SET percentage = ? WHERE id = ?";
        
	
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setFloat(1, cl.getPercentage());
            stmt.setObject(2, cl.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
                
    }
    
    public boolean delete(Countrylanguage cl) {
        
        String sql = "DELETE FROM countrylanguage WHERE id = ?";
        
	
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setObject(1, cl.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
                
    }
}
