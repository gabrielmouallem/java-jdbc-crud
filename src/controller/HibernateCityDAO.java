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
import model.City;
import controller.HibernateDAO;
import org.hibernate.Transaction;

public class HibernateCityDAO {
    City city;
    Transaction transaction;
    
    public void addCity (City city){
        HibernateDAO dao = new HibernateDAO();
        try {
            dao.save(city);
            startTransaction();
            commitTransaction();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        dao.closeSession();
    }
    
    public void deleteCity (City city, int id){
        HibernateDAO dao = new HibernateDAO();
        try {
            dao.load(city, id);
            dao.delete(city);
            startTransaction();
            commitTransaction();
        } catch (Exception e){
            e.printStackTrace();
        }
        dao.closeSession();
    }
    
 
    public void updateCity (City city, int id){
        
    }
    

    public void startTransaction(){
        HibernateDAO dao = new HibernateDAO();
        transaction = dao.getSession().beginTransaction();
    }
    

    public void commitTransaction (){
        transaction.commit();
    }
}

