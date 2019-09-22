/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Gabriel Mouallem
 * @author Gabriel Zanon
 * @author Breno Nesto
 */
import model.Country;
import controller.HibernateDAO;
import org.hibernate.Transaction;

public class HibernateCountryDAO {
    Country country;
    Transaction transaction;
    
    public void addCountry (Country country){
        HibernateDAO dao = new HibernateDAO();
        try {
            dao.save(country);
            startTransaction();
            commitTransaction();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        dao.closeSession();
    }
    
    public void deleteCountry (Country country, String code){
        HibernateDAO dao = new HibernateDAO();
        try {
            dao.load(country, code);
            dao.delete(country);
            startTransaction();
            commitTransaction();
        } catch (Exception e){
            e.printStackTrace();
        }
        dao.closeSession();
    }
    
 
    public void updateCountry (Country country, String code){
        
    }
    

    public void startTransaction(){
        HibernateDAO dao = new HibernateDAO();
        transaction = dao.getSession().beginTransaction();
    }
    

    public void commitTransaction (){
        transaction.commit();
    }
}

