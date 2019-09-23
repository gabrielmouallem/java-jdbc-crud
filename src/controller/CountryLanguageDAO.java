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
        
        String sql = "INSERT INTO countrylanguage (countrycode, language, isofficial, percentage) VALUES (?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cl.getId().getCountrycode());
            stmt.setString(2, cl.getId().getLanguage());
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
                
                CountrylanguageId clid = new CountrylanguageId();
                clid.setCountrycode(rs.getString("countrycode"));
                clid.setLanguage(rs.getString("language"));
                cl.setId(clid);
                
                cl.setIsofficial(rs.getBoolean("isofficial"));
                cl.setPercentage(rs.getFloat("percentage"));
                cls.add(cl);
                
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        String result = "--------- TODAS AS LÍNGUAS REGISTRADAS: --------- \n\n";
        result += "LÍNGUA -------- PÁIS ------------ É OFICIAL ---------- (%) \n\n";
        for (int i=0; i<cls.size();i++){
            result += cls.get(i).getId().getLanguage()+"\t" + cls.get(i).getId().getCountrycode() + "\t"+ cls.get(i).isIsofficial()+ "\t"+ cls.get(i).getPercentage() +"\n";
        }
        JTextArea textArea = new JTextArea(result);
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "PESQUISA DE LÍNGUAS",  
                                       JOptionPane.YES_NO_OPTION);
        return cls;
    }
    
    //Edited: the parameter was an object and now is these 3 values
    public boolean update(String countryCode, float percentage, String language) {
        
        String sql = "UPDATE countrylanguage SET percentage = ? WHERE countrycode = ? and language = ?";
        
	
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setFloat(1, percentage);
            stmt.setString(2, countryCode);
            stmt.setString(3, language);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Língua editada com sucesso!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao editar língua: "+ex);
            System.err.println("Erro: "+ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
                
    }
    
    public boolean delete(Countrylanguage cl) {
        
        String sql = "DELETE FROM countrylanguage WHERE countrycode = ? and language = ?";
        
	
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cl.getId().getCountrycode());
            stmt.setString(2, cl.getId().getLanguage());
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
