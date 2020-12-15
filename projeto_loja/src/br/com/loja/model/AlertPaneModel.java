/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author maico
 */
public class AlertPaneModel {

    private static AlertPaneModel instance;

    public static AlertPaneModel getInstance() {
        if (instance == null) {
            instance = new AlertPaneModel();
        }
        return instance;
    }

    private AlertPaneModel() {
    }
    
    public void setAlertPaneDefault(Pane pane, Label label) {
        pane.setStyle("-fx-background-color: #FFFFFF;");
        label.setText("Preencha os campos a seguir para cadastrar um cliente");
    }

    public void setAlertPaneSuccess(Pane pane, Label label, String message) {
        pane.setStyle("-fx-background-color: #47B071;");
        label.setText(message);
    }

    public void setAlertPaneFail(Pane pane, Label label, String message) {
        pane.setStyle("-fx-background-color: #FF0000;");
        label.setText(message);
    }
}
