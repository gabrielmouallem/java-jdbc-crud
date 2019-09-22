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
import javax.swing.JOptionPane;
import model.City;

/**
 *
 * @author breno
 */
public class CityDAO {
    
    private Connection con = null;

    public CityDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(City city) {
        
        String sql = "INSERT INTO city (id, name, countrycode, district, population) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, city.getId());
            stmt.setString(2, city.getName());
            stmt.setString(3, city.getCountrycode());
            stmt.setString(4, city.getDistrict());
            stmt.setInt(5, city.getPopulation());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cidade adicionada com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar cidade: "+ex);
            System.err.println("Erro: "+ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<City> findAll() {
        
        String sql = "SELECT * FROM city";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<City> citys = new ArrayList<>();
        
        try {
            
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                
                City city = new City();
                
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setCountrycode(rs.getString("countrycode"));
                city.setDistrict(rs.getString("district"));
                city.setPopulation(rs.getInt("population"));
                citys.add(city);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return citys;
    }
    
    public boolean update(City city) {
        
        String sql = "UPDATE city SET name = ? WHERE id = ?";
        
	
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, city.getName());
            stmt.setInt(2, city.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
                
    }
    
    public boolean delete(City city) {
        
        String sql = "DELETE FROM city WHERE id = ?";
        
	
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, city.getId());
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
