/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.view;

import br.com.loja.connection.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

/**
 *
 * @author maico
 */
public class FXMLMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("FXMLClient.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select version()";
        String result = (String) session.createNamedQuery(sql).getSingleResult();
        System.out.println(result);
        session.getTransaction().commit();
        session.close();
    }

}
