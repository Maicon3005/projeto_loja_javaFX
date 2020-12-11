/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.utilities;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ClearFields {

    private static ClearFields instance;

    public static ClearFields getInstance() {
        if (instance == null) {
            instance = new ClearFields();
        }
        return instance;
    }

    private ClearFields() {
    }

    public void clearScreen(AnchorPane anchorPane) {
        ObservableList components = anchorPane.getChildren();
        components.stream().filter((component) -> (component instanceof TextField)).forEachOrdered((component) -> {
            ((TextField) component).setText(null);
        });
    }

}
