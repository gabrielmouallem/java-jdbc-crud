package controller;


import java.io.Serializable;
import org.hibernate.*;
import model.HibernateUtil;
import java.util.ArrayList;
/**
 *
 * @author Gabriel Mouallem
 * @author Gabriel Zanon
 * @author Breno Nesto
 */
public class HibernateDAO {
    Session session;

    public HibernateDAO() {
        session = (Session) HibernateUtil.getSessionFactory().openSession();
    }
    
    public Session getSession() {
        return session;
    }

    public void setSession(Session sessao) {
        this.session = sessao;
    }


    public void save(Object entity) {
        session.save(entity);
    }

    public Object load(Object entity, Serializable id) {
        session.load(entity, id);
        return entity;
    }

    public void delete(Object entity) {
        session.delete(entity);
    }

    public void update(Object entity) {
        session.update(entity);
    }

    public void closeSession() {
        session.close();
    }
    

    public ArrayList<Object> listAll(String table) {
        return (ArrayList<Object>) session.createQuery("from "+table).list();
    }
}
