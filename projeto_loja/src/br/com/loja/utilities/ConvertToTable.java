/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.loja.utilities;

import br.com.loja.model.ClientModel;
import br.com.loja.model.TableClientModel;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maico
 */
public class ConvertToTable {

    private static ConvertToTable instance;

    public static ConvertToTable getInstance() {
        if (instance == null) {
            instance = new ConvertToTable();
        }
        return instance;
    }

    private ConvertToTable() {

    }

    public List<TableClientModel> convertObject(List<ClientModel> clientList) {
        List<TableClientModel> listTableClient = new ArrayList<>();
        TableClientModel tableClientModel;

        for (ClientModel clientModel : clientList) {
            Long id = clientModel.getId();
            String name = clientModel.getName();
            String cpf = clientModel.getCpf();
            String email = clientModel.getContact().getEmail();
            String cellPhone = clientModel.getContact().getCellPhone();
            String telephone = clientModel.getContact().getTelephone();

            tableClientModel = new TableClientModel(id, name, cpf, email, cellPhone, telephone);
            listTableClient.add(tableClientModel);
        }
        return listTableClient;
    }
}
