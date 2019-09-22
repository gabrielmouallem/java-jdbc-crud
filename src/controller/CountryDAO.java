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
/**
 *
 * @author breno
 */
public class CountryDAO {
    
    private Connection con = null;

    public CountryDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Country country) {
        
        String sql = "INSERT INTO country (code, city, name, continent, region, surfacearea, indepyear, population, lifeexpectancy, gnp, gnpold, localname, governmentform, headofstate, code2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, country.getCode());
            stmt.setObject(2, country.getCity());
            stmt.setString(3, country.getName());
            stmt.setString(4, country.getContinent());
            stmt.setString(5, country.getRegion());
            stmt.setFloat(6, country.getSurfacearea());
            stmt.setShort(7, country.getIndepyear());
            stmt.setInt(8, country.getPopulation());
            stmt.setFloat(9, country.getLifeexpectancy());
            stmt.setBigDecimal(10, country.getGnp());
            stmt.setBigDecimal(11, country.getGnpold());
            stmt.setString(12, country.getLocalname());
            stmt.setString(13, country.getGovernmentform());
            stmt.setString(14, country.getHeadofstate());
            stmt.setString(15, country.getCode2());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
        public List<Country> findAll() {
        
        String sql = "SELECT * FROM country";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Country> countrys = new ArrayList<>();
        
        try {
            
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                
                Country country = new Country();
                
                country.setCode(rs.getString("code"));
                country.setCity((City) rs.getObject("city"));
                country.setName(rs.getString("name"));
                country.setContinent(rs.getString("continent"));
                country.setRegion(rs.getString("region"));
                country.setSurfacearea(rs.getFloat("surfacearea"));
                country.setIndepyear(rs.getShort("indepyear"));
                country.setPopulation(rs.getInt("population"));
                country.setLifeexpectancy(rs.getFloat("lifeexpectancy"));
                country.setGnp(rs.getBigDecimal("gnp"));
                country.setGnpold(rs.getBigDecimal("gnpold"));
                country.setLocalname(rs.getString("localname"));
                country.setGovernmentform(rs.getString("governmentform"));
                country.setHeadofstate(rs.getString("headofstate"));
                country.setCode2(rs.getString("code2"));
                countrys.add(country);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return countrys;
    }
        
     public boolean update(Country country) {
        
        String sql = "UPDATE country SET name = ? WHERE code = ?";
        
	
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, country.getName());
            stmt.setString(2, country.getCode());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
                
    }   
     
     public boolean delete(Country country) {
        
        String sql = "DELETE FROM country WHERE code = ?";
        
	
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, country.getCode());
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
