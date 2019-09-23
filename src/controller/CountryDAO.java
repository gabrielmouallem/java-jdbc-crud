/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import connection.ConnectionFactory;
import java.awt.Dimension;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
        
        String sql = "INSERT INTO country (code, capital, name, continent, region, surfacearea, indepyear, population, lifeexpectancy, gnp, gnpold, localname, governmentform, headofstate, code2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, country.getCode());
            stmt.setInt(2, country.getCity().getId());
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
        
        List<Country> countries = new ArrayList<>();
        
        try {
            
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                
                Country country = new Country();
                
                country.setCode(rs.getString("code"));
                
                City city = new City();
                city.setId(rs.getInt("capital"));
                
                country.setCity(city);
                
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
                countries.add(country);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
                String result = "--------- TODOS OS PAÍSES REGISTRADAS: --------- \n\n";
        result += "NOME, CONTINENTE, POPULAÇÃO, EXPECTATIVA DE VIDA, FORMA DE GOVERNO, CHEFE DE ESTADO\n\n";
        for (int i=0; i<countries.size();i++){
            result += countries.get(i).getName() + "\t\t" + countries.get(i).getContinent() + "\t" + countries.get(i).getPopulation() + "\t\t" + countries.get(i).getLifeexpectancy() + "\t" + countries.get(i).getGovernmentform() + "\t\t" + countries.get(i).getHeadofstate() + "\n";
        }
        JTextArea textArea = new JTextArea(result);
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 1500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "PESQUISA DE PAÍSES",  
                                       JOptionPane.INFORMATION_MESSAGE);
        
        return countries;
    }
        
        public List<Country> report(float min, float max) {
        
        String sql = "SELECT * FROM country WHERE lifeexpectancy BETWEEN ? AND ?;";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Country> countries = new ArrayList<>();
        
        try {
            
            
            stmt = con.prepareStatement(sql);
            stmt.setFloat(1, min);
            stmt.setFloat(2, max);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                
                Country country = new Country();
                
                country.setCode(rs.getString("code"));
                
                City city = new City();
                city.setId(rs.getInt("capital"));
                
                country.setCity(city);
                
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
                countries.add(country);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        String result = "--------- PAÍSES COM EXPECTATIVA DE VIDA ENTRE "+ min +" E " + max + " --------- \n\n";
        result += "PÁIS \t\t\t EXPECTATIVA DE VIDA\n\n";
        for (int i=0; i<countries.size();i++){
            result += countries.get(i).getName() + " \t\t\t " + countries.get(i).getLifeexpectancy() +"\n";
        }
        JTextArea textArea = new JTextArea(result);
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "RELATÓRIO DE PAÍSES",  
                                       JOptionPane.INFORMATION_MESSAGE);
        return countries;
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

