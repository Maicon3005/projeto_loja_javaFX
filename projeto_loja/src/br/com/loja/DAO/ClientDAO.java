/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.DAO;

import br.com.loja.connection.HibernateUtil;
import br.com.loja.model.AlertPaneModel;
import br.com.loja.model.ClientModel;
import java.util.ArrayList;
import java.util.List;
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
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Long idClient = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            idClient = (Long) session.save(client);
            transaction.commit();
        } catch (HibernateError e) {
            transaction.rollback();
            AlertPaneModel.getInstance().alertException("Erro ao Salvar", "O seguinte erro ocorreu ao tentar salvar o cliente", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return idClient;
    }
    
    public void updateClient(ClientModel clienteModel) {
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(clienteModel);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            AlertPaneModel.getInstance().alertException("Erro ao Atualizar", "O seguinte erro ocorreu ao tentar atualizar o cliente", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }
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
            AlertPaneModel.getInstance().alertException("Erro ao Deletar", "O seguinte erro ocorreu ao tentar deletar o cliente", e.toString());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    public ClientModel searchById(Long idClient) {
        ClientModel clientModel = null;
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            clientModel = (ClientModel) session.get(ClientModel.class, idClient);
            transaction.commit();
        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar o cliente", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return clientModel;
    }

    public ClientModel searchByName(String nameClient) {
        ClientModel clientModel = null;
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(ClientModel.class);
            criteria.add(Restrictions.eq("name", nameClient));
            clientModel = (ClientModel) criteria.uniqueResult();;
        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar o cliente", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return clientModel;
    }

    public List<ClientModel> searchByNameList(String nameClient) {
        List<ClientModel> listClient = null;
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(ClientModel.class);
            criteria.add(Restrictions.eq("name", nameClient));
            listClient = criteria.list();
        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar o cliente", e.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return listClient;
    }

    public List<ClientModel> searchAllClients() {
        List<ClientModel> clientList = new ArrayList<>();
        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            session = sessionFactory.openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ClientModel> criteriaQuery = criteriaBuilder.createQuery(ClientModel.class);
            criteriaQuery.from(ClientModel.class);
            clientList = session.createQuery(criteriaQuery).getResultList();

        } catch (HibernateException e) {
            AlertPaneModel.getInstance().alertException("Erro ao Buscar", "O seguinte erro ocorreu ao tentar buscar o cliente", e.toString());
        } finally {
            session.close();
        }
        return clientList;
    }
}
