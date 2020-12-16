/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.DAO;

import br.com.loja.connection.HibernateUtil;
import br.com.loja.model.ClientModel;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Criteria;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author maico
 */
public class ClientDAO {

    public Long SaveClient(ClientModel client) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Long idClient = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            idClient = (Long) session.save(client);
            transaction.commit();
        } catch (HibernateError e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return idClient;
    }

    public ClientModel searchByName(String nameClient) {
        ClientModel clientModel = null;
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(ClientModel.class);
            criteria.add(Restrictions.eq("name", nameClient));
            clientModel = (ClientModel) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return clientModel;
    }

    public boolean removeClient(ClientModel clientModel) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(clientModel);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }
}
